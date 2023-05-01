

declare global {
    function initMap(): void;
}


export function initMap(): void {
        let mapInstance: google.maps.Map;
        navigator.geolocation.getCurrentPosition((position) => {
            const origin = {lat: position.coords.latitude, lng: position.coords.longitude};


            mapInstance = new google.maps.Map(
                document.getElementById('mape') as HTMLElement,
                {
                    zoom: 6.5,
                    center: {lat: 36.876156, lng: 10.187846},
                    zoomControl: false,
                    scaleControl: true,

                }
            );
            // Create the initial InfoWindow.
            let infoWindow = new google.maps.InfoWindow({
                content: 'Click the map to get Lat/Lng!',
                position: origin,
            });

            infoWindow.open(mapInstance);
            // Configure the click listener.
            mapInstance.addListener('click', (mapsMouseEvent) => {
                // Close the current InfoWindow.
                infoWindow.close();
                // Create a new InfoWindow.
                infoWindow = new google.maps.InfoWindow({
                    position: mapsMouseEvent.latLng,
                });
                infoWindow.setContent(
                    JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
                );
                infoWindow.open(mapInstance);
            });

            const geocoder = new google.maps.Geocoder();
            const infowindow = new google.maps.InfoWindow();

            (document.getElementById('submit') as HTMLElement).addEventListener(
                'click',
                () => {
                    geocodeLatLng(geocoder, mapInstance, infowindow);
                }
            );


            function geocodeLatLng(
                geocoderr: google.maps.Geocoder,
                mapObj: google.maps.Map,
                infowindoww: google.maps.InfoWindow) {
                const input = (document.getElementById('latlng') as HTMLInputElement).value;
                const latlngStr = input.split(',', 2);
                const latlng = {
                    lat: parseFloat(latlngStr[0]),
                    lng: parseFloat(latlngStr[1]),
                };

                geocoderr.geocode(
                    {location: latlng},
                    (results: google.maps.GeocoderResult[], status: google.maps.GeocoderStatus) => {
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
