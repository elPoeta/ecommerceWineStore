class LoginRegistro{
    static async iniciarSesion(){
        console.log('login');
        let logEmail = document.querySelector('#log-email');
        let logPass = document.querySelector('#log-pass');
        const URL_LOGIN_SERVER = 'api/LoginServer';
        
        let loginUsuario ={};
        loginUsuario.email = logEmail.value;
         loginUsuario.password = logPass.value;
        console.log(loginUsuario);
       try
       {
           
        const log = await Http.post(URL_LOGIN_SERVER,loginUsuario); 
         const data = await log.json();
          console.log('log >>> ',data);
           if(data !== null && data !== 'error'){
              location.replace("index.html");
               console.log('log >>> ',data);
           }else{
                      LoginRegistro.msgLogueo("Error al iniciar sesion",'msg-color-error');

           }
                    
        }catch (error){
            console.log("error",error);
       LoginRegistro.msgLogueo("Error al iniciar sesion "+err,'msg-color-error');
        }
       
    
    }
    
    static async crearCuenta(){
        console.log('crear cuenta');
        let signNombre = document.querySelector('#sign-nombre');
        let signApellido = document.querySelector('#sign-apellido');
        let signEmail = document.querySelector('#sign-email');
        let signPass = document.querySelector('#sign-pass');
        let signConfirm = document.querySelector('#sign-confirm');
         const URL_REGISTRO_SERVER = 'api/RegistroServer';
        let registroUsuario ={};
        registroUsuario.nombre = signNombre.value;
        registroUsuario.apellido = signApellido.value;
        registroUsuario.email = signEmail.value;
        registroUsuario.password = signPass.value;
        registroUsuario.confirmPassword = signConfirm.value;
        console.log(registroUsuario);
   try{
       const reg = await Http.post(URL_REGISTRO_SERVER,registroUsuario);
        const data = await reg.json(); 
           if(data !== null && data !== 'error'){
            //window.location.replace("index.html");
                    LoginRegistro.msgLogueo("Registro completado Inicia Sesion",'msg-color-ok')
                    sw2();
           }else{
                      LoginRegistro.msgLogueo("Error al crear cuenta",'msg-color-error');

           }
           
        } catch (err){
       console.log("error",err);
       LoginRegistro.msgLogueo("Error al crear cuenta "+err, 'msg-color-error');
     }
   

    }
    
   static msgLogueo(msg,color){
    let template = 
            `<div class="mensaje-login">
                 <p class="${color}">${msg}</p>
             </div>`;
    document.querySelector('#panel-login-msg').innerHTML=template;
    
  }
}


/*
 
let logEmail = document.querySelector('#log-email');
let logPass = document.querySelector('#log-pass');
let btnLogin = document.querySelector('#btn-login');
const URL_LOGIN_SERVER = 'LoginServer';

btnLogin.addEventListener('click', (e)=>{
     console.log('log >',e);
   let loginUsuario ={};
   loginUsuario.email = logEmail.value;
   loginUsuario.password = logPass.value;
   console.log(loginUsuario);

   Http.post(URL_LOGIN_SERVER,loginUsuario)
           .then(response => response.json())
  .then( data => {
       console.log('log >>> ',data);
           if(data !== null && data !== 'error'){
                location.replace("index.html");
           }else{
                      msgLogueo("Error al iniciar sesion",'msg-color-error');

           }
                    
    }).catch (err => {
       console.log("error",err);
       msgLogueo("Error al iniciar sesion "+err,'msg-color-error');
});
  
});

function msgLogueo(msg,color){
    let template = 
            `<div class="mensaje-login">
                 <p class="${color}">${msg}</p>
             </div>`;
    document.querySelector('#panel-login-msg').innerHTML=template;
    
}


let signNombre = document.querySelector('#sign-nombre');
let signApellido = document.querySelector('#sign-apellido');
let signEmail = document.querySelector('#sign-email');
let signPass = document.querySelector('#sign-pass');
let signConfirm = document.querySelector('#sign-confirm');
let btnRegistrar = document.querySelector('#btn-registrar');

btnRegistrar.addEventListener('click', (e)=>{
    console.log('reg >',e);
   let registroUsuario ={};
   registroUsuario.nombre = signNombre.value;
   registroUsuario.apellido = signApellido.value;
   registroUsuario.email = signEmail.value;
   registroUsuario.password = signPass.value;
   registroUsuario.confirmPassword = signConfirm.value;
  console.log(registroUsuario);
   Http.post('RegistroServer',registroUsuario)
           .then(response => response.json())
        .then( data => {
           if(data !== null && data !== 'error'){
            //window.location.replace("index.html");
                    msgLogueo("Registro completado Inicia Sesion",'msg-color-ok')
                    sw2();
           }else{
                      msgLogueo("Error al crear cuenta",'msg-color-error');

           }
                    
    }).catch (err => {
       console.log("error",err);
       msgLogueo("Error al crear cuenta "+err, 'msg-color-error');
});
   
});
 */