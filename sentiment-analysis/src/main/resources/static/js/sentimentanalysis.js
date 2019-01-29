 window.addEventListener("load", function () {
      function callAnalyze() {
        var XHR = new XMLHttpRequest();
        var FD = new FormData(form);
        XHR.addEventListener("load", function(event) {
          var responseJson = JSON.parse(event.target.response);
          positiverank.innerHTML = responseJson.ranking.positive;
          negativerank.innerHTML = responseJson.ranking.negative;
          neutralrank.innerHTML = responseJson.ranking.neutral;
          results.style.display='block';
        });
        XHR.addEventListener("error", function(event) {
          alert('Oups! Quelque chose s\'est mal passé.');
        });
        XHR.open("POST", "/analyze/text");
        XHR.send(text.value);
        results.style.display='none';
      }
      var form = document.getElementById("analyze");
      var text = document.getElementById('text');
      var results = document.getElementById('results');
      var library = document.getElementById('library');
      var positiverank = document.getElementById('positiverank');
      var negativerank = document.getElementById('negativerank');
      var neutralrank = document.getElementById('neutralrank');
      form.addEventListener("submit", function (event) {
        event.preventDefault();
        callAnalyze();
      });
});