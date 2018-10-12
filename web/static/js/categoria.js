class Categoria{
    static async consultar(){
        const URL_CATEGORIA_SERVER = 'api/CategoriaServer';
        try{
            const categorias = await Http.get(URL_CATEGORIA_SERVER);
            return categorias;
        }catch (err){
           console.log(`Error: ${err}`); 
        }
    }
    
    static async cargarDropMenu(){
        try{
            const categorias = await Categoria.consultar();
            let template =
                       ` <div class="column">
                            <h3>Todos</h3>
                            <hr/>
                         <a href="#" onclick=Producto.buscarPorCategoria(0);>Ver Todos</a> 
                        </div>
                           ${categorias.map( categoria =>
                   ` <div class="column">
                               <h3>${categoria.nombre}</h3>
                                <hr/>
                          <a href="#" onclick=Producto.buscarPorCategoria(${categoria.id});>Todos</a>
                          ${categoria.subCategorias.map( sub =>
                          `<a href="#" onclick=Producto.buscarPorSubCategoria(${sub.id});>${sub.nombre}</a>`
                        ).join('')}</div>`
                     ).join('')}`;
           document.querySelector('#panel-dropMenu').innerHTML = template;
  
        }catch(err){
                console.log(`Error: ${err}`);
        }
    }
   
   static dropMenu(ev) {
    if(!ev.classList.contains("change")){
        document.querySelector('.dropdown-content').style.display='grid';
    
    }else{
        document.querySelector('.dropdown-content').style.display='none';
       
    }
    ev.classList.toggle("change"); 
}
}

Categoria.cargarDropMenu();

