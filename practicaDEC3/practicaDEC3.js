function pidoAlumnos() {
    fetch("servidorDEC3.php?op=descarga")
        .then(function (respuesta) {
            return respuesta.json()
        }).then(function (alumnos) {
            alert("Alumnos descargados")
            creoRecuadros(alumnos)
        })
}


function creoRecuadros(alumnos) {

    let contenido = document.getElementById("contenido")

    for (let alumno of alumnos) {
        let divAlumno = document.createElement("div")
        divAlumno.setAttribute("id", "info-" + alumno.nombre)

        let nombre = document.createElement("h3")
        nombre.innerHTML = alumno.nombre

        let txtarea = document.createElement("TEXTAREA")

        let botonEnviar = document.createElement("button")
        botonEnviar.innerHTML = "Enviar"


        contenido.appendChild(divAlumno)
        divAlumno.appendChild(nombre)
        divAlumno.appendChild(txtarea)
        divAlumno.appendChild(botonEnviar)

        botonEnviar.addEventListener("click", mandaIncidencias(nombre, txtarea))
    }

}


function mandaIncidencias(nombre, txtarea) {
    return function () {

        let data = new FormData()
        data.append('op', 'actualiza')
        data.append('nom', nombre.innerHTML)
        data.append('incidencias', txtarea.value)


        fetch("servidorDEC3.php", {
            method: "POST",
            body: data,
        })
            .then(function (respuesta) {
                if (respuesta.ok) {
                    alert("Incidencias actualizadas")
                    console.log(txtarea.value)
                    console.log(nombre.innerHTML)
                    txtarea.value = "";
                }
            })
    }
}

window.addEventListener("load", pidoAlumnos)

