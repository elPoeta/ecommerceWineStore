let pos = 0;
let turno = 0;
let txtBanner = ["Poesía embotellada", "Tienda de vinos"];
let tipeo = document.querySelector('#tipeo-contenedor');
const speed = 250;

setTimeout(autoTipeo, speed);

function autoTipeo() {
  if (pos < txtBanner[turno].length) {
    tipeo.innerHTML += txtBanner[turno].charAt(pos);
    pos++;
    setTimeout(autoTipeo, speed);
  } else {
  	setTimeout(borrarTipeo, speed+100);
  }
}

function borrarTipeo() {
	if (pos >= 0) {
      var str=txtBanner[turno].toString().substring(0, pos);
      tipeo.innerHTML = str;
      pos--;
      setTimeout(borrarTipeo, speed-100);
    } else {
      turno++;
      if(turno>=txtBanner.length) 
        turno=0;
      setTimeout(autoTipeo, speed);
    }
}

const login = document.querySelector('.login');

login.addEventListener('click', e => {
    if(document.querySelector('#login-header')){
        location.replace('login.html');
    }else
         if(document.querySelector('#logout-header'))
           {
             if(!document.querySelector('#logout')){
                 
                 Usuario.panelUser();
                 document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
            } 
            else{
               
               document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
              }
           
        }
  
});

let loadingsvg = document.querySelector("#loading");  
function loading(on) {
  
  if (on) {
      loadingsvg.style.display = "block";
  } else {
      loadingsvg.style.display = "none";
  }
}