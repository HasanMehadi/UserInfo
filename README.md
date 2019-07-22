# userinfo

goto project folder->src->main->resources.

open application.properties.

edit datasource path, username,password according to your system

Back end run process: go to root project folder. open command line. type: mvn spring-boot:run

Front end run process: goto project folder->src->resources->static->userinfo.

remove node_modules folder(if any exists).

open command line. type: npm install. new node_modules folder will generate.

type: ng serve.

open your browser. type localhost:4200
