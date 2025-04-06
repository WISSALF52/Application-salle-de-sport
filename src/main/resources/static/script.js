document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

function redirectToReservation(planType, planPrice) {
    window.location.href = `reservation?plan=${encodeURIComponent(planType)}&price=${encodeURIComponent(planPrice)}`;
}