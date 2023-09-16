/// <reference types="@types/googlemaps" />

declare global {
    function initMap(): void;
}


export function initMap() {
    const map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 33.8439408, lng: 9.400138 },
        zoom: 6.5,
    });
    const input = document.getElementById('my-input');
    const autocomplete = new google.maps.places.Autocomplete(
        input as HTMLInputElement,
        {
            fields: ['place_id', 'geometry', 'name', 'formatted_address'],
        }
    );

    autocomplete.bindTo('bounds', map);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    const infowindow = new google.maps.InfoWindow();
    const infowindowContent = document.getElementById('infowindow-content');

    infowindow.setContent(infowindowContent);

    const geocoder = new google.maps.Geocoder();
    const marker = new google.maps.Marker({ map });

    marker.addListener('click', () => {
        infowindow.open(map, marker);
    });

    // Add a click event listener to the map
    map.addListener('click', (event) => {
        infowindow.close();
        marker.setPosition(event.latLng);
        marker.setVisible(true);

        // Log the latitude and longitude of the clicked location to the console
        const lat = event.latLng.lat();
        const lng = event.latLng.lng();
        console.log(`Latitude: ${lat}, Longitude: ${lng}`);

        // Reverse geocode the clicked location and display the address in the info window
        geocoder.geocode({ location: event.latLng }, (results, status) => {
            if (status === 'OK' && results[0]) {
                infowindowContent.children['place-name'].textContent = '';
                infowindowContent.children['place-id'].textContent = '';
                infowindowContent.children['place-address'].textContent =
                    results[0].formatted_address;
                infowindow.open(map, marker);
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    });

    autocomplete.addListener('place_changed', () => {
        infowindow.close();

        const place = autocomplete.getPlace();

        if (!place.place_id) {
            return;
        }
        geocoder.geocode({ placeId: place.place_id }, (results, status) => {
            if (status === 'OK' && results[0]) {
                map.setZoom(11);
                map.setCenter(results[0].geometry.location);
                // Set the position of the marker using the place ID and location.
                // @ts-ignore TODO This should be in @typings/googlemaps.
                marker.setPlace({
                        placeId: place.place_id,
                        location: results[0].geometry.location,
                    }
                );
                console.log(`Location: ${map}`);

                marker.setVisible(true);
                infowindowContent.children['place-name'].textContent = place.name;
                infowindowContent.children['place-id'].textContent = place.place_id;
                infowindowContent.children['place-address'].textContent =
                    results[0].formatted_address;
                infowindow.open(map, marker);
                const loc = results[0].geometry.location;
                // Log the latitude and longitude of the selected place to the console
                const lat = results[0].geometry.location.lat();
                const lng = results[0].geometry.location.lng();
                console.log(`Latitude: ${lat}, Longitude: ${lng}`);
                console.log(`Location: ${loc}`);

            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    });
}

window.initMap = initMap;
