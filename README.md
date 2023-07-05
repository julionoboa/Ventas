## About the project

This is a simple sales system programmed in Java, utilizing MySQL as the database and JDBC as the driver. The project operates in the console.

### Features
- Sales Management: The system allows users to manage sales, including creating new sales, updating existing sales and deleting sales records.

- Air Conditioner Management: The system provides funcionality to manage air conditioners, such as adding new models, updating existing models and removing models from the inventory.

### Database Structure

The system utilizes a MySQL database with the following tables:
1. **Venta (Sales)**: This table stores information about sales, including the sale ID, AC brand, AC type and sale amount.

2. **Aire (Air Conditioner)**:  This table stores details about the air conditioners, including important information like AC ID, brand, type and price.

### Setup
To run this project locally, please verify the followings:

- Java Development Kit (JDK) installed on your machine.

- MySQL Server installed and running.

- JDBC driver for MySQL (you may need to add the .jar to the project folder).

1. Clone this repository to your local machine.

  ```sh
  git clone https://github.com/julionoboa/Ventas.git
  ```
2.  Import the project into your Java IDE.

3. Setup the MySQL database by executing this SQL script:
```sql
create table Aire (
idAire int not null auto_increment primary key,
marcaAire varchar(100) not null,
tipoAire varchar(100) not null,
cantidadAire int not null,
precioAire decimal(10,2) not null
);
```
```sql
create table Venta (
idVenta int not null primary key auto_increment,
marcaVenta varchar(100) not null,
tipoVenta varchar(100) not null,
cantidadVenta int not null,
totalVenta decimal(10,2)
);
```
```sql
alter table venta add column idAire int, add foreign key (idAire) references Aire(idAire);
```
4. Configure the database connection in the project. Update the JDBC connection URL, username and password in the code to match your MySQL database settings (check AireDAOImplementation and VentaDAOImplementation classes).

5. Build and run the project un your Java IDE!

### Usage
Once the project is up and running, you can interact with the sales system through the console. Just follow the prompts to navigate through the menu and available options, such managing sales and air conditioners.

--------------------------------------------------------------------------------------------

Español

--------------------------------------------------------------------------------------------


## Sobre el proyecto
Este es un simple programa de gestión de ventas programado en Java, utilizando MySQL como base de datos y JDBC como driver.

### Características
- Gestión de ventas: El sistema permite a los usuarios mantener el control de sus ventas y efectuar operaciones como crear nuevas ventas, actualizar y borrar ventas existentes.

- Gestionar inventario de Aires Acondicionados: El sistema cuenta con funcionalidades para gestionar aires aconidiconados, como agregar nuevos modelos, actualizar y borrar modelos existentes.

### Estructura de Base de Datos

El sistema utiliza una base de datos MySQL con las siguientes tablas:
1. **Venta**: Esta tabla guarda información sobre ventas, como el ID de la venta, la marca, el tipo y el monto total.

2. **Aire**: Esta tabla guarda detalles sobre los aires acondicionados, tales como el ID, la marca, el tipo y el precio.

### Configuración
Para poner a funcionar este proyecto, por favor verifique que tenga instalado lo siguiente:

- Java Development Kit (JDK) instalado en tu PC.

- MySQL Server instalado y funcionando.

- JDBC driver para MySQL (quizás necesites agregar el .jar manual al folder del proyecto).


1. Clona este repositorio a tu máquina.

  ```sh
  git clone https://github.com/julionoboa/Ventas.git
  ```
2.  Importa el proyecto a tu IDE de Java preferido.

3.  Crea las siguientes tablas dentro de tu base de datos MySQL:
```sql
create table Aire (
idAire int not null auto_increment primary key,
marcaAire varchar(100) not null,
tipoAire varchar(100) not null,
cantidadAire int not null,
precioAire decimal(10,2) not null
);
```
```sql
create table Venta (
idVenta int not null primary key auto_increment,
marcaVenta varchar(100) not null,
tipoVenta varchar(100) not null,
cantidadVenta int not null,
totalVenta decimal(10,2)
);
```
```sql
alter table venta add column idAire int, add foreign key (idAire) references Aire(idAire);
```
4. Configura la conexión a base de datos en el proyecto, actualiza la URL, el nombre de usuario y la contraseña del JDBC driver (puedes encontrar esas configuraciones en las clases **AireDAOImplementation y VentaDAOImplementation**)

5. ¡Compila y ejecuta el proyecto en tu IDE y listo!

### Uso
Una vez el proyecto esté funcionando y ejecutándose, podrás interactuar con el sistema de ventas a través de la consola. Solo debes seguir las opciones e instrucciones del menú ya creado, con opciones para gestionar las ventas y los aires acondicionados.

