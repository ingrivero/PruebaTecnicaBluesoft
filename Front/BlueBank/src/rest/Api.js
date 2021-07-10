const URL = "http://localhost:9090/";

const ENPOINT_REGISTER_USER = URL+"users/register";
const ENPOINT_GET_USER = URL+"users/get/";
const ENPOINT_GET_ALL_USERS = URL+"users/all";


const ENPOINT_GET_ALL_CUENTAS = URL+"cuentas/all";
const ENPOINT_GET_CUENTA = URL+"cuentas/get/";
const ENPOINT_REGISTER_CUENTA = URL+"cuentas/register";
const ENPOINT_UPDATE_CUENTA = URL+"cuentas/update";
const ENPOINT_DELETE_CUENTA = URL+"cuentas/delete/";

const ENPOINT_GET_TRANSACCIONES = URL+"transacciones/get/";
const ENPOINT_REGISTER_TRANSACCION = URL+"transacciones/register";
const ENPOINT_DELETE_TRANSACCION = URL+"transacciones/delete/";


  async function cuentasList(){
    var data = {};
    var query = null;
    try{
      query = await fetch(ENPOINT_GET_ALL_CUENTAS, {
        method: 'GET',
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        }
      });
      data = await query.json();

    }catch(error){
      console.log(query);
      data =  {
        status:false,
        message:error,
      }
    }
    return data;
  }

  async function getAllUsers(){
    var data = {};
    var query = null;
    try{
      query = await fetch(ENPOINT_GET_ALL_USERS, {
        method: 'GET',
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        }
      });
      data = await query.json();

    }catch(error){
      console.log(query);
      data =  {
        status:false,
        message:error,
      }
    }
    return data;
  }

  async function getUser(userId){
    var data = {};
    try{
      const query = await fetch(ENPOINT_GET_USER +userId, {
        method: 'GET',
        credentials: "same-origin",
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        },
      });
      data = await query.json();

    }catch(error){
      data =  {
        status:false,
        message:error,
      }
    }
    return data;

  }

  async function registerUser(user){
    var data = {};
    var query = null;

    try{
       query = await fetch(ENPOINT_REGISTER_USER, {
        method: 'POST',
        headers: {
          Accept: "application/json, text/plain, */*",
          'Content-Type':'application/json'
          //'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify({
          nombre: user.nombre,
          apellido: user.apellido,
          correo: user.correo,
          cedula:user.cedula,
        })
      });
      data = await query.json();

    }catch(error){
      console.log(query);
      data =  {
        status:false,
        mensaje:error,
      }
    }
    return data;

  }

  async function getCuenta(cuentaId){
    var data = {};
    try{
      const query = await fetch(ENPOINT_GET_CUENTA +cuentaId, {
        method: 'GET',
        credentials: "same-origin",
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        },
      });
      data = await query.json();

    }catch(error){
      data =  {
        status:false,
        message:error,
      }
    }
    return data;

  }

  async function registerCuenta(cuenta){
    var data = {};
    var query = null;

    try{
       query = await fetch(ENPOINT_REGISTER_CUENTA, {
        method: 'POST',
        headers: {
          Accept: "application/json, text/plain, */*",
          'Content-Type':'application/json'
          //'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify({
          userId: cuenta.userId,
          tipo: cuenta.tipo,
          saldo: cuenta.saldo,
        })
      });
      data = await query.json();

    }catch(error){
      console.log(query);
      data =  {
        status:false,
        mensaje:error,
      }
    }
    return data;

  }

  async function updateCuenta(cuenta){

      var data = {};

      try{
        const query = await fetch(ENPOINT_UPDATE_CUENTA, {
          method: 'PUT',
          credentials: "same-origin",
          headers: {
            Accept: "application/json, text/plain, */*",
            'Content-Type':'application/json'
            //'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: JSON.stringify({
            id:cuenta.id,
            userId: cuenta.userId,
            tipo: cuenta.tipo,
            saldo: cuenta.saldo,
          })
        });
        data = await query.json();

      }catch(error){
        data =  {
          status:false,
          message:error,
        }
      }
      return data;

  }

  async function deleteCuenta(cuentaId){
    var data = {};
    try{
      const query = await fetch(ENPOINT_DELETE_CUENTA +cuentaId,{
        method: 'DELETE',
        credentials: "same-origin",
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        },
      });
      data = await query.json();

    }catch(error){
      data =  {
        status:false,
        message:error,
      }
    }
    return data;
  }

  async function getTransacciones(cuentaId){
    var data = {};
    try{
      const query = await fetch(ENPOINT_GET_TRANSACCIONES +cuentaId, {
        method: 'GET',
        credentials: "same-origin",
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        },
      });
      data = await query.json();

    }catch(error){
      data =  {
        status:false,
        message:error,
      }
    }
    return data;

  }

  async function registerTransaccion(transaccion){
    var data = {};
    var query = null;

    try{
       query = await fetch(ENPOINT_REGISTER_TRANSACCION, {
        method: 'POST',
        headers: {
          Accept: "application/json, text/plain, */*",
          'Content-Type':'application/json'
          //'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify({
          userId: transaccion.userId,
          cuentaId: transaccion.cuentaId,
          tipo: transaccion.tipo,
          monto: transaccion.monto,
        })
      });
      data = await query.json();

    }catch(error){
      console.log(query);
      data =  {
        status:false,
        mensaje:error,
      }
    }
    return data;

  }

  async function deleteTransaccion(transaccionId){
    var data = {};
    try{
      const query = await fetch(ENPOINT_DELETE_TRANSACCION +transaccionId,{
        method: 'DELETE',
        credentials: "same-origin",
        headers: {
          "Accept": "application/json, text/plain, */*",
          'Content-Type':'application/json'
        },
      });
      data = await query.json();

    }catch(error){
      data =  {
        status:false,
        message:error,
      }
    }
    return data;
  }

  function sleep(ms){
    return(new Promise(function(resolve, reject) {
        setTimeout(function() { resolve(); }, ms);
    }));
  }

const exportedObject ={
  URL, sleep, getAllUsers, cuentasList, getUser, registerUser, getCuenta, registerCuenta, updateCuenta, deleteCuenta,
  getTransacciones, registerTransaccion, deleteTransaccion
};

export default exportedObject;
