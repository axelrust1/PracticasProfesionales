
# API de Simulacion de Banco

## Descripción
Esta API permite gestionar clientes en un banco, permitiendo registrar, eliminar y editar clientes. A su vez, los clientes pueden generar cuentas bancarias y realizar transacciones como transferencias, depósitos y retiros.

## Tecnologías Utilizadas
- Java
- Maven
- Spring Boot
- MySQL

## Requisitos Previos
Para ejecutar esta API es necesario tener instalado:
- **Java 17** o superior
- **Maven**
- **MySQL**
- Un entorno de desarrollo como **IntelliJ IDEA**, **Eclipse** o, como en mi caso, **Visual Studio Code**

## Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/axelrust1/PracticasProfesionales
   ```
2. Configurar la base de datos MySQL:
   - Crear una base de datos llamada `banco`.
   - Configurar `application.properties` con los datos de conexión.

## Rutas Disponibles
### **Clientes**
#### **Crear un cliente**
**POST** `/cliente`

**Request Body:**
```json
{
  "nombre": "Axel",
  "apellido": "Rust",
  "dni": 44882713
  "fechaNacimiento": "2003-05-30",
  "tipoPersona": "F",
  "cuentas": []
}
```

**Respuesta Exitosa:**
```json
{
  "nombre": "Axel",
  "apellido": "Rust",
  "dni": 44882713
  "fechaNacimiento": "2003-05-30",
  "tipoPersona": "PERSONA_FISICA"
  "cuentas": []
}
```

#### **Buscar Cliente por DNI**
**GET** `/cliente/{dni}`

**Respuesta:**
```json
{
  "nombre": "Axel",
  "apellido": "Rust",
  "dni": 44882713
  "fechaNacimiento": "2003-05-30",
  "tipoPersona": "PERSONA_FISICA",
  "cuentas": []
}
```

#### **Eliminar Cliente**
**DELETE** `/cliente/{dni}`

**Respuesta:**
```json
{
  "mensaje": "Cliente con DNI 44882713 eliminado exitosamente."
}
```
### **Cuentas**

#### **Crear una Cuenta**
**POST** `/cuenta`

**Request Body:**
```json
{
  "tipoCuenta": "A",
  "dniCliente": 44882713,
  "moneda": "P",
  "movimientos": []
}
```

**Respuesta:**
```json
{
  "tipoCuenta": "CAJA_AHORRO",
  "dniCliente": 44882713
  "moneda": "PESOS",
  "movimientos": []
}
```

#### **Buscar Cuenta por Número**
**GET** `/cuenta/{numeroCuenta}`

**Respuesta:**
```json
{
  "tipoCuenta": "CAJA_AHORRO",
  "dniCliente": 44882713
  "moneda": "PESOS",
  "movimientos": []
}
```

#### **Buscar Cuentas por DNI**
**GET** `/cuenta/dni/{dni}`

**Respuesta:**
```json
[
  {
  "tipoCuenta": "CAJA_AHORRO",
  "dniCliente": 44882713
  "moneda": "PESOS",
  "movimientos": []
}
]
```

#### **Listar Movimientos de una Cuenta**
**GET** `/cuenta/{numeroCuenta}/transacciones`

**Respuesta:**
```json
{
  "numeroCuenta": 4992823,
  "transacciones": [
    {
      "fecha": "2024-02-15",
      "tipo": "DEPOSITO",
      "descripcionBreve": "Depósito en efectivo",
      "monto": 5000.0
    }
  ]
}
```

---
### **Transferencias**

#### **Realizar una Transferencia**
**POST** `/api/transfer`

**Request Body:**
```json
{
  "cuentaOrigen": 1001,
  "cuentaDestino": 1002,
  "monto": 5000.0,
  "moneda": "PESOS"
}
```

**Respuesta Exitosa:**
```json
{
  "estado": "EXITOSA",
  "mensaje": " "
}
```

**Respuesta Fallida:**
```json
{
  "estado": "FALLIDA",
  "mensaje": "Saldo insuficiente"
}
```

#### **Realizar un Depósito**
**POST** `/api/deposito`

**Request Body:**
```json
{
  "cuentaOrigen": 1001,
  "monto": 10000.0,
  "moneda": PESOS
}
```

**Respuesta Exitosa:**
```json
{
  "estado": "EXITOSO",
  "mensaje": "Depósito Exitoso"
}
```

#### **Realizar un Retiro**
**POST** `/api/retiro`

**Request Body:**
```json
{
  "cuentaOrigen": 1001,
  "monto": 3000.0,
  "moneda": "PESOS"
}
```

**Respuesta Exitosa:**
```json
{
  "estado": "EXITOSO",
  "mensaje": "Retiro Exitoso"
}
```

**Respuesta Fallida:**
```json
{
  "estado": "FALLIDA",
  "mensaje": "Saldo insuficiente"
}
```
### Todo lo explicado esta subido a Render para ser utilizado por una web
