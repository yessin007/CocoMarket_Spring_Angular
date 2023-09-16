/// <reference types="@types/googlemaps" />

declare global {
    function initMap(): void;
}

export function initMap(): void {
    let mapInstance: google.maps.Map;
    navigator.geolocation.getCurrentPosition((position) => {
        const origin = { lat: position.coords.latitude, lng: position.coords.longitude };

        mapInstance = new google.maps.Map(document.getElementById('mape') as HTMLElement, {
            zoom: 6.5,
            center: { lat: 36.876156, lng: 10.187846 },
            zoomControl: false,
            scaleControl: true,
        });

        // Create the initial InfoWindow.
        let infoWindow = new google.maps.InfoWindow({
            content: 'Click the map to get address!',
            position: origin,
        });

        infoWindow.open(mapInstance);

        // Configure the click listener.
        mapInstance.addListener('click', (mapsMouseEvent) => {
            const latlng = mapsMouseEvent.latLng;

            const latitudeInput = document.getElementById('latitude') as HTMLInputElement;
            latitudeInput.value = latlng.lat().toString();

            const longitudeInput = document.getElementById('longitude') as HTMLInputElement;
            longitudeInput.value = latlng.lng().toString();

            // Close the current InfoWindow.
            infoWindow.close();

            // Create a new InfoWindow.
            infoWindow = new google.maps.InfoWindow({
                position: latlng,
            });

            // Geocode the clicked location to get its address.
            const geocoder = new google.maps.Geocoder();
            geocoder.geocode({ location: latlng }, (results, status) => {
                if (status === 'OK') {
                    if (results[0]) {
                        infoWindow.setContent(results[0].formatted_address);
                        infoWindow.open(mapInstance);
                    } else {
                        window.alert('No results found');
                    }
                }
            });
        });

        const geocoder = new google.maps.Geocoder();
        const infowindow = new google.maps.InfoWindow();

        (document.getElementById('submit') as HTMLElement).addEventListener('click', () => {
            geocodeLatLng(geocoder, mapInstance, infowindow);
        });

        function geocodeLatLng(
            geocoderr: google.maps.Geocoder,
            mapObj: google.maps.Map,
            infowindoww: google.maps.InfoWindow
        ) {
            const input = (document.getElementById('latlng') as HTMLInputElement).value;
            const latlngStr = input.split(',', 2);
            const latlng = {
                lat: parseFloat(latlngStr[0]),
                lng: parseFloat(latlngStr[1]),
            };

            geocoderr.geocode({ location: latlng }, (results: google.maps.GeocoderResult[], status: google.maps.GeocoderStatus) => {
                if (status === 'OK') {
                    if (results[0]) {
                        mapObj.setZoom(11);

                        const marker = new google.maps.Marker({
                            position: latlng,
                            map: mapObj,
                        });

                        infowindoww.setContent(results[0].formatted_address);
                        infowindoww.open(mapObj, marker);
                    } else {
                        window.alert('No results found');
                    }
                } else {
                    window.alert('Geocoder failed due to: ' + status);
                }
            });
        }
    });
}

window.initMap = initMap;
