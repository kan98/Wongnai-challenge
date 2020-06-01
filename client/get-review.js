const urlParams = new URLSearchParams(window.location.search);
const idParam = urlParams.get('id');

const request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/reviews/' + idParam, true);

request.onreadystatechange = function() {
    document.getElementById("id").innerHTML=idParam;
    if (this.readyState == 4 && this.status == 200) {
        const data = JSON.parse(this.response);
        document.getElementById("review").innerHTML=data.review;
    } else {
        document.getElementById("review").innerHTML="Error! We could not find a review for the ID. Please go back and try again.";
    }
}

request.send()