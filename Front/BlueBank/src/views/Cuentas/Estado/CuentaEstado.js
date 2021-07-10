import { useState, useEffect} from "react";
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Table from 'react-bootstrap/Table';
import Badge from 'react-bootstrap/Badge';
import API from "../../../rest/Api";

import {
  useParams
} from "react-router-dom";

function CuentaEstado(props) {

  let paramsRoute = useParams();

  const idCuenta = paramsRoute.id;
  const [isLoading, setLoading] = useState(false);
  const [user, setUser] = useState({});
  const [cuenta, setCuenta] = useState({});
  const [transacciones, setTransacciones] = useState([]);

  const getCuenta = async () => {
    setLoading(true);
    var result = await API.getCuenta(idCuenta);
    if(result.status){
      setCuenta(result.data);
      getUser(result.data.userId);
      getTransacciones(result.data.userId, idCuenta);
    }
    else{
      var error = result.message;
      alert(error);
    }
  };

  const getUser = async (userId) => {
    var result = await API.getUser(userId);
    if(result.status){
      setUser(result.data);
    }
    else{
      var errors = result.message + "\n\n";
      for (var error of result.data) {
        errors += error.defaultMessage + "\n";
      }
      alert(errors);
    }
    //setLoading(false);
  };

  const getTransacciones = async (userId, cuentaId) => {
    var result = await API.getTransacciones(userId, cuentaId);
    if(result.status){
      setTransacciones(result.data);
    }
    else{
      var errors = result.message + "\n\n";
      for (var error of result.data) {
        errors += error.defaultMessage + "\n";
      }
      alert(errors);
    }
    setLoading(false);
  };

  useEffect(() => {
    getCuenta();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if(isLoading){
    return(
      <div>
      <Container>
        <h2>Cargando...</h2>
      </Container>
      </div>
    );
  }
  else{
    return (
        <div>
        <Container>
          <h4>ESTADO DE CUENTA</h4>
          <Card>
            <Card.Body>
              <Card.Title>{user.nombre} {user.apellido}</Card.Title>

              <Row xs={2} md={4} lg={6}>
                <Col><strong>Numero Cuenta:</strong></Col>
                <Col>{cuenta.id}</Col>
              </Row>
              <Row xs={2} md={4} lg={6}>
                <Col><strong>Tipo:</strong></Col>
                <Col>{cuenta.tipo}</Col>
              </Row>
              <Row xs={2} md={4} lg={6}>
                <Col><strong>Saldo:</strong></Col>
                <Col>$ {cuenta.saldo}</Col>
              </Row>

              <hr/>

              <Card.Text>
                Transacciones receintes
              </Card.Text>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    <th>ID Transaccion</th>
                    <th>ID Usuario</th>
                    <th>Cuenta</th>
                    <th>Tipo</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                {
                  transacciones.map((transaccion, index) => (
                    <tr key = {index}>
                      <td>{transaccion.id}</td>
                      <td>{transaccion.userId}</td>
                      <td>{transaccion.cuentaId}</td>
                      <td>{transaccion.tipo}</td>
                      <td>{(transaccion.tipo == "Retiro") ? <Badge variant="danger">-</Badge> : <Badge variant="success">+</Badge>} $ {transaccion.monto}</td>
                      <td>{transaccion.fecha}</td>
                      <td>
                        <ButtonGroup aria-label="Basic example">
                          <Button variant="danger" onClick = {( ) => {} }>Eliminar</Button>
                        </ButtonGroup>
                      </td>
                    </tr>
                  ))
                }
                </tbody>
              </Table>

            </Card.Body>
          </Card>
        </Container>
        </div>
    );
  }
}

export default CuentaEstado;
