import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Row from 'react-bootstrap/Row';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
  Link
} from "react-router-dom";

import CuentaCrear from './views/Cuentas/Crear/CuentaCrear';
import CuentaEstado from './views/Cuentas/Estado/CuentaEstado';
import CuentaLista from './views/Cuentas/Lista/CuentaLista';
import TransaccionCrear from './views/Transaccion/Crear/TransaccionCrear';

function App() {
  return (
    <Router>
    <div className="container">
      <div className = "m-5">
        <nav className = "mb-5">
          <Row>
            <ButtonGroup aria-label="Basic example">
              <Link to="/cuentas/all" className = "m-2">
                <Button variant="primary" onClick = {() => {} }>Cuentas</Button>
              </Link>
              <Link to="/cuentas/crear" className = "m-2">
                <Button variant="primary" onClick = {() => {} }>Crear cuenta</Button>
              </Link>

              <Link to={"/transaccion/crear"} className = "m-2">
                <Button variant="primary mr-1" onClick = {() => {} }>Hacer una transaccion</Button>
              </Link>
            </ButtonGroup>
          </Row>
        </nav>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/cuentas/crear">
            <CuentaCrear />
          </Route>
          <Route path="/cuentas/consultar/:id">
            <CuentaEstado />
          </Route>
          <Route exact path="/cuentas/all">
            <CuentaLista />
          </Route>
          <Route path="/transaccion/crear">
            <TransaccionCrear />
          </Route>
          <Route exact path="/">
            <Redirect
              to={{
                pathname: "/cuentas/all",
              }}
            />
          </Route>
        </Switch>
      </div>
    </div>
    </Router>
  );
}

export default App;
