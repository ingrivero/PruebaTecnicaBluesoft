import { useState, useEffect} from "react";
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Table from 'react-bootstrap/Table';
import {
  Link
} from "react-router-dom";

import API from "../../../rest/Api";

function CuentaLista() {

  const [isLoading, setLoading] = useState(false);
  const [cuentas, setCuentas] = useState([]);

  const cuentasList = async () => {
    setLoading(true);
    var result = await API.cuentasList();
    setLoading(false);
    if(result.status){
      setCuentas(result.data);
    }
    else{
      console.log("Error en la consulta");
    }
  };


  useEffect(() => {
    cuentasList();
  }, []);

  if(isLoading){
    return(
      <h2>Cargando..</h2>
    );
  }else{
    return (
        <div>
          <h4>LISTA DE CUENTAS</h4>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Numero cuenta</th>
                <th>Tipo de cuenta</th>
                <th>ID usuario</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
            {
              cuentas.map((cuenta, index) => (
                <tr key = {index}>
                  <td>{cuenta.id}</td>
                  <td>{cuenta.tipo}</td>
                  <td>{cuenta.userId}</td>
                  <td>
                    <ButtonGroup aria-label="Basic example">
                      <Link to={"/cuentas/consultar/"+cuenta.id}>
                        <Button variant="primary mr-1" onClick = {() => {} }>Cosultar</Button>
                      </Link>
                      <Button variant="danger" onClick = {( ) => {} }>Eliminar</Button>
                    </ButtonGroup>
                  </td>
                </tr>
              ))
            }
            </tbody>
          </Table>
        </div>
    );
  }
}

export default CuentaLista;
