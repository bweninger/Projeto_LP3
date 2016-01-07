var meuMapa;
function init() {
    meuMapa = new ol.Map({
        target: 'MeuMapa',
        renderer: 'canvas',
        view: new ol.View({
            projection: 'EPSG:900913',
            center: [-5193252.61684, -2698365.38923],
            zoom: 18
        })
    });
    var openStreetMapLayer = new ol.layer.Tile({
        source: new ol.source.OSM()
    });
    meuMapa.addLayer(openStreetMapLayer);

    var login = $("#loginUsuario").val();
    var urlString = 'http://localhost:8080/AppEnterpriseWeb/LP3Rest/lp3/posicoes/' + login;

    var vectorSource = new ol.source.Vector();

    $.ajax({
        url: urlString,
        success: function (data) {
            var iconFeature = new ol.Feature({
                geometry: new ol.geom.Point([-5193252.61684, -2698365.38923]),
            });

            vectorSource.addFeature(iconFeature);

            var posicoes = data.getElementsByTagName("posicao");
            for (var i = 0; i < posicoes.length; i++) {
                var lat = parseFloat(posicoes[i].getElementsByTagName("lat")[0].childNodes[0].nodeValue);
                var lon = parseFloat(posicoes[i].getElementsByTagName("lon")[0].childNodes[0].nodeValue);
                var coordinate = ol.proj.transform([lon, lat],
                        'EPSG:4326', 'EPSG:900913');

                iconFeature = new ol.Feature({
                    geometry: new ol.geom.Point(coordinate),
                });

                vectorSource.addFeature(iconFeature);
            }

            var vectorLayer = new ol.layer.Vector({
                source: vectorSource
            });
            meuMapa.addLayer(vectorLayer);
        },
        error: function (e) {
            console.log(e.message);
        },
        type: 'GET'
    });

    var element = document.getElementById('popup');
    var popup = new ol.Overlay({
        element: element,
        positioning: 'bottom-center',
        stopEvent: false
    });
    meuMapa.addOverlay(popup);
    meuMapa.on('click', function (evt) {
        var feature = meuMapa.forEachFeatureAtPixel(evt.pixel,
                function (feature, layer) {
                    return feature;
                });
        if (feature) {
            popup.setPosition(evt.coordinate);
            var xmlString;
            var login = $("#loginUsuario").val()
            var urlString = 'http://localhost:8080/AppEnterpriseWeb/LP3Rest/lp3/avatar/';
            console.log(urlString);
            urlString = urlString.concat(login);
            console.log(urlString);
            $.ajax({
                url: urlString,
                success: function (data) {
                    $(element).popover({
                        'placement': 'top',
                        'html': true,
                        'content': '<img src="' + data + '" style="width:304px;height:304px;"/>'
                    });
                    $(element).popover('show');
                },
                error: function (e) {
                    console.log(e.message);
                },
                type: 'GET'
            });
        } else {
            $(element).popover('destroy');
        }
    });
    meuMapa.on('pointermove', function (e) {
        if (e.dragging) {
            $(element).popover('destroy');
            return;
        }
        var pixel = meuMapa.getEventPixel(e.originalEvent);
        var hit = meuMapa.hasFeatureAtPixel(pixel);
    });
}