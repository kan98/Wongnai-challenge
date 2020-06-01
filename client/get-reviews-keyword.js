const urlParams = new URLSearchParams(window.location.search);
const keywordParam = urlParams.get('keyword');
var output = keywordParam.replace(/&#x[0-9A-Fa-f]+;/g,
    function(htmlCode) {
        var codePoint = parseInt( htmlCode.slice(3, -1), 16 );
        var utf8 = String.fromCharCode( codePoint );
    });

var elem = document.createElement("p");
elem.innerHTML = output;

const request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/reviews/?query=' + elem.textContent, true);

request.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        const data = JSON.parse(this.response);
        window.console.log(data);
    } else {
    }
}

request.send()