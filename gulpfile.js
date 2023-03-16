const gulp = require("gulp");
var fs = require('fs');
var GulpSSH = require('gulp-ssh');
const { series, src, dest } = require("gulp");
var exec = require('child_process').exec

//Configuración ssh para la conexión con AWS utilizando un certificado
var config = {
  host: 'iaweb.duckdns.org',
  port: 22,
  username: 'ec2-user',
  privateKey: fs.readFileSync('C:/Users/Juan/Downloads/MiClaveAws.pem')
}

//Asignamos la configuración a la variable gulpSSH que se utilizará en la tareas de conexión con aws
var gulpSSH = new GulpSSH({
  ignoreErrors: false,
  sshConfig: config
})

//Tarea para acceder mediantes ssh a AWS y nos clonamos el repositorio de git
function conexionawsgit() {
  return gulpSSH
  .shell(['cd /home/ec2-user/', 'git clone  https://github.com/ies-franciscodelosrios/ia-web-backend.git',  ' git clone https://github.com/ies-franciscodelosrios/ia-web-frontend.git]', {filePath: 'shell.log'}])
    .pipe(gulp.dest('logs'))
}

//Tarea para compilar y generar el fichero jar
function awscompilejar() {
  return gulpSSH
  .shell(['cd /home/ec2-user/ia-web-backend', '.\mvnw install' , {filePath: 'shell.log'}])
    .pipe(gulp.dest('logs'))
}
  
//Tarea para compilar angular
function awscompileangular() {
  return gulpSSH
  .shell(['cd /home/ec2-user/ia-web-frontend', 'ng serve' , {filePath: 'shell.log'}])
    .pipe(gulp.dest('logs'))
}


//Generamos la imagen de sprin-boot la cual generamos el jar
function despliegueawsdocker() {
  return gulpSSH
    .exec(['docker build -t app .'])
}

//Generamos un servicio multicontenedor
function despliegueawsdockercompose() {
  return gulpSSH
    .exec(['docker-compose up'])
}

//Especificamos los nombres de cada tarea
exports.conexionawsgit = conexionawsgit;
exports.awscompilejar = awscompilejar;
exports.awscompileangular = awscompileangular
exports.despliegueawsdocker = despliegueawsdocker;
exports.despliegueawsdockercompose = despliegueawsdockercompose;

//Creamos dos listas de tareas en serie
exports.toDo = series(conexionawsgit,awscompilejar,awscompileangular,despliegueawsdocker,despliegueawsdockercompose);
