function validateForm1() {
    var x = document.forms["get-review"]["id"].value;
    if (x == "" || isNaN(x)) {
      alert("Please enter a number in the id field.");
      return false;
    }
}

function validateForm2() {
  var x = document.forms["get-reviews-keyword"]["keyword"].value;
  if (x == "") {
    alert("Please do not leave the keyword field blank.");
    return false;
  }
}

function validateForm3() {
  var x = document.forms["edit-review"]["id2"].value;
  if (x == "" || isNaN(x)) {
    alert("Please enter a number in the id field.");
    return false;
  }
  var y = document.forms["edit-review"]["review"].value;
  if (y == "") {
    alert("Please do not leave the review field blank.");
    return false;
  }
}

var myForm = document.getElementById('edit-review');
myForm.addEventListener('submit',updateReview,false);

function updateReview() {
  validateForm3();
  
  var id = document.forms["edit-review"]["id2"].value;
  var review = document.forms["edit-review"]["review"].value;

  const request = new XMLHttpRequest();
  request.open('PUT', 'http://localhost:8080/reviews/' + id, true);

  body = "{\"review\":" + review + "}";

  request.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          document.getElementById("update-status").innerHTML="Success! The review is updated!";
      } else {
          document.getElementById("update-status").innerHTML="Error! We could not update a review for the specified ID. Please try again.";
      }
  }

request.send(body)
}