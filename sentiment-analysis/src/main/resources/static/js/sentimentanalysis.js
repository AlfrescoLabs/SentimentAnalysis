function displayRanking(object, ranking) {
    var svgDoc = object.contentDocument;
    var element = svgDoc.getElementById('ranking');
    element.textContent =  ranking;
}

window.addEventListener("load", function () {
      var form = document.getElementById("analyze");
      var text = document.getElementById('text');
      var results = document.getElementById('results');
      var library = document.getElementById('library');
      var positivebee = document.getElementById('positivebee');
      var negativebee = document.getElementById('negativebee');
      var neutralbee = document.getElementById('neutralbee');
      form.addEventListener("submit", function (event) {
        event.preventDefault();
        callAnalyze();
      });

    function updateBeeRanking(bee, ranking) {
        if (bee.contentDocument) {
            displayRanking(bee, ranking);
        } else {
            bee.addEventListener("load",function() {
                displayRanking(this, ranking);
            }, false);
        }
    }

    function callAnalyze() {
        var XHR = new XMLHttpRequest();
        var FD = new FormData(form);
        XHR.addEventListener("load", function(event) {
            var responseJson = JSON.parse(event.target.response);
            updateBeeRanking(positivebee, responseJson.ranking.positive);
            updateBeeRanking(neutralbee, responseJson.ranking.neutral);
            updateBeeRanking(negativebee, responseJson.ranking.negative);
            results.style.visibility='visible';
        });
        XHR.addEventListener("error", function(event) {
          alert('Oups! Somehting went wrong.');
        });
        XHR.open("POST", "/analyze/text");
        XHR.send(text.value);
        results.style.visibility='hidden';
    }

});