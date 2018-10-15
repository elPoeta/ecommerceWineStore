class Producto{
    static async consultar(id){
        const URL_PRODUCTO_SERVER = 'api/ProductoServer?&q=';
        const productos = await Http.get(URL_PRODUCTO_SERVER +JSON.stringify(id));
        return productos;
    }
    
    static async buscarPorCategoria(id){
         const param = {"categoria":"" + id};
       try{
            loading(true);
        let productos = await Producto.consultar(param);
            Producto.viewProductos(productos);
            Producto.ocultarMenu();
       } catch(err){
           console.log(`Error: ${err}`);
       }
     loading(false);   
    }
    
      static async buscarPorSubCategoria(id){
        const param = {"subCategoria":"" + id};
        try{
            loading(true);
        let productos = await Producto.consultar(param);
            Producto.viewProductos(productos);  
            Producto.ocultarMenu();

       } catch(err){
           console.log(`Error: ${err}`);
       }
        loading(false);
    }
    
     static viewProductos(productos){
     
        let template =  `<section class="contenedor-productos text-center">
                         ${productos.map(producto =>
           `<div class="card">
            <div class="zona-lupa">
              <img src="${producto.imagen}" class="img-card" id="img-${producto.id}">
            </div>
            <h2 class="titulo-card">${producto.nombre}</h2>
            <h3 class="precio-card">$ ${producto.precio}</h3>
            <hr>
            <button class="btn-agregar" id="btn-agregar-${producto.id}" onclick="Carrito.agregarProducto(${producto.id})">Agregar</button>
            </div>`).join('')}</section>`;  
            document.querySelector('#paypal-button').style.display="none";
            document.querySelector('#panel-central').innerHTML = template;
            Lupa.lupaEventos();
            
    }
    
   static ocultarMenu(){
        
        document.querySelector('.dropdown-content').style.display='none';
        document.querySelector('.hamburger').classList.toggle("change");
        document.querySelector('.btn-menu').classList.toggle("change");
    }
     
}
