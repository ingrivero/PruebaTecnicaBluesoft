import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import API from "../../../rest/Api";

function CuentaCrear() {

  const saveCuenta = async () => {
    var user = {
      nombre:document.getElementById("nombre").value,
      apellido:document.getElementById("apellido").value,
      correo:document.getElementById("correo").value,
      cedula:document.getElementById("cedula").value,
    };
    var resultUser = await API.registerUser(user);
    console.log(resultUser);
    if(resultUser.status){
      var newUser = resultUser.data;
      var cuenta = {
        userId:newUser.id,
        tipo:"ahorros",
        saldo:0,
      };
      var resultCuenta = await API.registerCuenta(cuenta);
      if(resultCuenta.status){
        alert(resultCuenta.message);
      }
      else{
        var errorsCuenta = resultCuenta.message + "\n\n";
        for (var errorCuenta of resultCuenta.data) {
          errorsCuenta += errorCuenta.defaultMessage + "\n";
        }
        alert(errorsCuenta);
      }
    }
    else{
      var errorsUser = resultUser.message + "\n\n";
      for (var errorUser of resultUser.data) {
        errorsUser += errorUser.defaultMessage + "\n";
      }
      alert(errorsUser);
    }
  };


  return (
      <div>
      <Container>
        <h4>CREAR NUEVA CUENTA DE AHORROS</h4>
        <Form>
          <Form.Group controlId="nombre">
            <Form.Label>Nombre</Form.Label>
            <Form.Control type="text" placeholder="Nombre" required/>
          </Form.Group>

          <Form.Group controlId="apellido">
            <Form.Label>Apellido</Form.Label>
            <Form.Control type="text" placeholder="Apellidos" required/>
          </Form.Group>

          <Form.Group controlId="cedula">
            <Form.Label>Cedula</Form.Label>
            <Form.Control type="text" placeholder="Cedula" required/>
          </Form.Group>

          <Form.Group controlId="correo">
            <Form.Label>Correo</Form.Label>
            <Form.Control type="email" placeholder="Correo" required/>
          </Form.Group>

          <Button variant="primary" onClick={() => saveCuenta()}>
            Guardar
          </Button>

        </Form>
      </Container>
      </div>
  );
}

export default CuentaCrear;
