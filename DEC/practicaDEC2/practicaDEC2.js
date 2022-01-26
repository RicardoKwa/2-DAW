    
    let tareas = [] //En tareas vamos guardando cada objeto tarea, el id de cada tarea coincide con su posición en el array
    let total = 0 
    
    //----Función para insertar tareas en el array tareas []

    function insertaTarea(agendaTareas, tarea) { 
        agendaTareas[total] = tarea
        tarea.id = total
        total++
    }
    

    //-----Función que devuelve tarea a partir de id

    function dameTarea(agendaTareas, id) {
        return agendaTareas[id]
    }
    
    //-----2 Funciones para mostrar nombre tarea en la lista////////
    
    function muestroNombreTarea(tarea) { 
        let li = document.createElement("li")
        rellenaElementoLista(li, tarea)
        document.getElementById("lista").appendChild(li)
    }

    function rellenaElementoLista(li, tarea) {
        let a1 = document.createElement("a") 
        a1.innerHTML = tarea.nombre //tarea es un objeto con nombre , descripcion e id que añado
        a1.href = "#"
        a1.setAttribute("id", tarea.id)
        li.appendChild(a1) 
        a1.addEventListener("click", muestraInfoTarea) 
    }

    /////////////////////////////////////////////////////
    
    //-----Función que muestra toda la Info de esa tarea en el panel derecho

    function muestraInfoTarea() {

        let zonaInfo = document.getElementById("zonaInfo")
        zonaInfo.innerHTML = ""

        let id = parseInt(this.getAttribute("id"))
        let tarea = dameTarea(tareas, id)

        if (tarea == undefined) {
            return false
        }
        
        let divInfo = document.createElement("div")
        divInfo.setAttribute("id","infoTarea-" + tarea.id)//Para poder borrar la tarea completamente, asigno el id de la tarea al div

        let nombre = document.createElement("h1")
        nombre.innerHTML = tarea.nombre

        let descripcion = document.createElement("p")
        descripcion.innerHTML = tarea.descripcion

        let botonBorrar = document.createElement("button")
        botonBorrar.innerHTML = "Borrar"
        

        zonaInfo.appendChild(divInfo)
        divInfo.appendChild(nombre)
        divInfo.appendChild(descripcion)
        divInfo.appendChild(botonBorrar)

        botonBorrar.addEventListener("click", borrarTarea)//Evento que se le asinga al boton borrar
    }

    //---Funcíon crear para asignar evento de click al boton del panel Izquierdo

    function accionCrear() {

        let nombre = document.forms.addTarea['nombre'].value
        let descripcion = document.forms.addTarea['descripcion'].value
        let tarea = {
            nombre: nombre,
            descripcion: descripcion
        }
        insertaTarea(tareas, tarea)
        muestroNombreTarea(tarea)
    }

    //---Funcíon borrar para asignar evento de click al boton del panel Derecho

    function borrarTarea() {
        let id = parseInt(this.parentElement.getAttribute("id").split("-")[1]) //tomo el id del div padre(del botón)
        let borrarInfo = this.parentElement //div padre del boton
        document.getElementById("zonaInfo").removeChild(borrarInfo)
        let borrarLista = document.getElementById(id).parentNode //el padre del enlace de la tarea que es un <li>
        document.getElementById("lista").removeChild(borrarLista)//borramos el elemento <li>
        delete tareas[id];//también borro con delete la tarea del array
    }


    window.addEventListener("load", function init() {

        document.getElementById("nueva_t").addEventListener("click", accionCrear)

    })