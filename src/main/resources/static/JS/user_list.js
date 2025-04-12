document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById("userChart").getContext("2d");

    // Dummy data - replace with dynamic server data later
    const data = {
        labels: ['gmail.com', 'yahoo.com', 'outlook.com', 'others'],
        datasets: [{
            label: 'Users by Email Domain',
            data: [12, 5, 8, 3], // Replace with real counts
            backgroundColor: [
                '#007BFF',
                '#28A745',
                '#FFC107',
                '#DC3545'
            ],
            borderRadius: 6
        }]
    };

    new Chart(ctx, {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: { display: false },
                title: {
                    display: true,
                    text: 'User Distribution by Email Domain',
                    font: { size: 18 }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 1 }
                }
            }
        }
    });
});

	function makeEditable(element) {
	      let userId = element.id.split("_")[1]; 
		  console.log(userId);
	      let span = document.getElementById("username_" + userId);
	      let input = document.getElementById("input_username_" + userId);
	     // let saveBtn = document.getElementById("save_" + userId);

	      span.style.display = "none"; // Hide text
	      input.style.display = "inline-block"; // Show input
	      input.focus(); // Focus on input
	     // saveBtn.style.display = "inline-block"; // Show save button
	  }
	  function updateUsername(button) {
		let urlParams = button.getAttribute('data-url');
		console.log(urlParams);
				
	         fetch(`/updateUsername/${urlParams}`, {
	             method: "POST",
	             headers: {
	                 "Content-Type": "application/json"
	             },
	         })
	     }
