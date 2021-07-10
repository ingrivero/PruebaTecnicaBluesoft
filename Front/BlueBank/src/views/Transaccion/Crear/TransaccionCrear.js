import { useState, useEffect} from "react";
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import API from "../../../rest/Api";

function TransaccionCrear(props) {

  const [isLoading, setLoading] = useState(false);

  const [users, setUsers] = useState([]);

  const [cuentaId, setCuentaId] = useState(0);
  const [userId, setUserId] = useState(0);
  const [tipo, setTipo] = useState("Retiro");

  const handleChangedUserId = (event) => {
    setUserId(event.target.value);
  };
  const handleChangedCuenta= (event) => {
    setCuentaId(Number(event.target.value));
  };
  const handleChangedTipo= (event) => {
    setTipo(event.target.value);
  };


  const saveTransaccion = async () => {
    var transaccion = {
      userId: userId,
      cuentaId:cuentaId,
      monto:document.getElementById("monto").value,
      tipo:tipo,
    };
    console.log(transaccion);
    var result = await API.registerTransaccion(transaccion);
    if(result.status){
      alert(result.message);
    }
    else{
      alert(result.message);
    }
  };

  const getAllUsers = async () => {
    setLoading(true);
    var result = await API.getAllUsers();
    setLoading(false);
    if(result.status){
      if(result.data.lentgh>0){
        setUserId(result.data[0].id)
        setUsers(result.data);
      }
    }
    else{
      console.log("Error en la consulta");
    }
  };


  useEffect(() => {
    getAllUsers();
  }, []);

  if(isLoading){
    return(
      <h2>Cargando..</h2>
    );
  }else{
    return (
        <div>
        <Container>
          <h4>NUEVA TRANSACCION</h4>
          <Form>
            <Form.Group controlId="cuentaId">
              <Form.Label>Numero de cuenta</Form.Label>
              <Form.Control type="text" placeholder="Numero de cuenta" value={cuentaId} onChange = {handleChangedCuenta}required/>
            </Form.Group>

            <Form.Group controlId="tipo">
              <Form.Label>Sleccione un usuario que ejecuta la operacion</Form.Label>
              <Form.Control as="select" custom value={userId} onChange = {handleChangedUserId}>

                {users.map((user, index)=>{
                  return(  <option key= {index} value={user.id}>{user.nombre} {user.apellido}</option> );
                })}

              </Form.Control>
            </Form.Group>

            <Form.Group controlId="tipo">
              <Form.Label>Tipo de operacion</Form.Label>
              <Form.Control as="select" custom value={tipo} onChange = {handleChangedTipo}>
                <option value="Retiro">Retiro</option>
                <option value="Consignacion">Consignacion</option>
              </Form.Control>
            </Form.Group>

            <Form.Group controlId="monto">
              <Form.Label>Monto</Form.Label>
              <Form.Control type="number" placeholder="monto" required/>
            </Form.Group>

            <Button variant="primary" onClick={() => saveTransaccion()}>
              Ejecutar
            </Button>

          </Form>
        </Container>
        </div>
    );
  }
}

export default TransaccionCrear;
