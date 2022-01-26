//////////////RICARDO KWAPISZ PAREJO/////////////////////

////////////// ARRAY COCHES /////////////////////////////

    let coches = [
        {
            "marca": "Citroen",
            "modelo": "c8",
            "estado": "en uso",
            "totalkm": 120000
        },
        {
            "marca": "Peugeot",
            "modelo": "p6",
            "estado": "reservado",
            "totalkm": 10000
        },
        {
            "marca": "Ferrari",
            "modelo": "f9",
            "estado": "reservado",
            "totalkm": 1000
        },
        {
            "marca": "Bmw",
            "modelo": "b90",
            "estado": "disponible",
            "totalkm": 10000
        },
        {
            "marca": "Mercedes",
            "modelo": "m6",
            "estado": "disponible",
            "totalkm": 10000
        },
        {
            "marca": "Seat",
            "modelo": "Ibiza",
            "estado": "en uso",
            "totalkm": 15000
        },
        {
            "marca": "Dacia",
            "modelo": "Sandero",
            "estado": "reservado",
            "totalkm": 13000
        },
        {
            "marca": "Dacia",
            "modelo": "Duster",
            "estado": "disponible",
            "totalkm": 160000
        },
        {
            "marca": "Toyota",
            "modelo": "t7",
            "estado": "disponible",
            "totalkm": 180000
        }
    ]

/////////////////////// FUNCIÓN 1 MOSTRAR TABLA /////////////////////////////////////////////////

    function muestraCoches(array, i = 1) {
        document.writeln("<div class = 'tablaDatos'>")
        if(i == 1){
            document.writeln("<h4> Tabla Original </h4><br>") //Le he añadido a las tablas encabezados
        } else if (i == 2) {
            document.writeln("<h4> Tabla Ordenada por Km </h4><br>")
        } else {
            document.writeln("<h4> Tabla Actualizada , Coches Jubilados </h4><br>")
        }
        
        document.writeln("<table border=2>")
        document.writeln("<tr><th>Marca</th><th>Modelo</th><th>Estado</th><th>Totalkm</th></tr>")
        for (let i in array) {
            document.write("<tr>")
            document.writeln("<td>" + array[i].marca + "</td>")
            document.writeln("<td>" + array[i].modelo + "</td>")
            document.writeln("<td>" + array[i].estado + "</td>")
            document.writeln("<td>" + array[i].totalkm + "</td>")
            document.write("</tr>")
        }
        document.writeln("</table>")
        document.writeln("</div>")
    }

////////////////////// FUNCIÓN 2 , ORDENACIÓN POR KM (.SORT) /////////////////////////////////////


    function ordenarPorKm(array) {
        array.sort(function (a, b) {
            return parseFloat(b.totalkm) - parseFloat(a.totalkm);
        });

    }

/////////////////// FUNCION 3 , MODIFICAR (CALLBACK PARA EL MAP)///////////////////////////////////

    function modificar(coche) {
        if (coche.totalkm > 100000) {
            coche.estado = "jubilado"
        }
        return coche;
    }

////////////////// FUNCIÓN 4 , MOSTRAR LISTA RESERVADOS ///////////////////////////////////////////

    function mostrarReservados(array) {
        document.writeln("<div class = 'listaReservados'>")
        document.writeln("<h4> Lista coches Reservados </h4><br>")
        document.writeln("<ul>")
        for (let i in array) {
            if (array[i].estado == "reservado") {
                document.writeln("<li>" + array[i].marca + " " + array[i].modelo + "</li>")
            }
        }
        document.writeln("</ul>")
        document.writeln("</div>")
    }

    // EJECUCION DE LAS FUNCIONES
        
    muestraCoches(coches);//Muestra primera tabla
    ordenarPorKm(coches);//Se ordenan los coches
    muestraCoches(coches,2);//Se muestra la 2ª tabla . El 2 representa que es la segunda tabla, para que salga un encabezado

    let cochesmod = coches.map(modificar);//Se modifica el estado de los coches de > 100000 km
    muestraCoches(cochesmod,3);//Se muestra la 3ª y última tabla actualizada con los coches jubilados 
    mostrarReservados(cochesmod);//Por último se muestra lista de los coches reservados
