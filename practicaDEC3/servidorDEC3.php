<?php

class Alumno
{
    public $nombre;

    public function __construct($nombre)
    {
        $this->nombre = $nombre;
    }
}

$a1 = new Alumno("Jose Antunez");

$a2 = new Alumno("Marta Gilarte");

$a3 = new Alumno("Fernando Perez");

$a4 = new Alumno("Laura Jimenez");

$a5 = new Alumno("Carmen Soria");

$alumnos = [$a1,$a2,$a3,$a4,$a5];          //Array de objetos alumno que mandamos



/**************************************************************************************/


$opcion=$_REQUEST['op'];

if ($opcion == "descarga"){                //Se manda el json con los alumnos al html
    echo json_encode($alumnos);
}

if ($opcion == "actualiza"){               //Se actualiza el fichero con las incidencias
    $nom = $_REQUEST["nom"];
    $incidencias=$_REQUEST["incidencias"];  
    $fp = fopen("incidencias.txt","a+");
    fwrite($fp,$nom);
    fwrite($fp,"\t\t");  
    fwrite($fp,$incidencias);
    fwrite($fp,"\r\n");
    fclose($fp);
}


?>