document.addEventListener('DOMContentLoaded', () => {
    const items = document.querySelectorAll('.carousel-item');
    const dots = document.querySelectorAll('.dot');
    let currentIndex = 0;
    let interval;
  
    function showService(index) {
      items.forEach((item, i) => {
        item.classList.toggle('active', i === index);
      });
  
      dots.forEach((dot, i) => {
        dot.classList.toggle('active', i === index);
      });
  
      currentIndex = index;
      clearInterval(interval);
      interval = setInterval(nextService, 5000); // Reinicia el intervalo
    }
  
    function nextService() {
      const nextIndex = (currentIndex + 1) % items.length;
      showService(nextIndex);
    }
  
    dots.forEach((dot, index) => {
      dot.addEventListener('click', () => showService(index));
    });
  
    interval = setInterval(nextService, 4000); // Cambia cada 5 segundos
    showService(currentIndex); // Mostrar el primer servicio
  });