import { useState } from 'react'; 
import Container from '@material-ui/core/Container';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import useStyles from './style';
import cliente from '../axios';
import MakeSnackBar from '../SnackBar/index';
import { useDispatch, useSelector } from 'react-redux';


export default function UrlCadastro(){
    const classes = useStyles();

    //const context = useSelector(state => state);

    const dispatch = useDispatch();

    const [url, setUrl] = useState('');

    const [openSnackbar, setOpenSnackbar] = useState(false);

function addUrl(e){
    e.preventDefault();
    cliente.post('/url/usuario/1',{
        value:url
    }

    ).then(function (response) {
        if(response.status === 201){
            setUrl('');
            console.log(dispatch)
            dispatch({type:'UPDATE', open:true})
            dispatch({type:'UPDATE_MESSAGE', message:response.data.message})
           //setMessage(response.data.message)
        }
  })
  .catch(function (error) {
    console.log(error);
  });
}

function handlerUrl(e){
    setUrl(e.target.value)
}

    return (
    <Container>
        <form onSubmit={ addUrl }>
            <Grid container spacing={2}>
                <Grid item sm={12}>
                    <h1>Cadastrar url</h1>
                </Grid>
                <Grid item sm={9}>
                    <TextField value={ url } onChange={ handlerUrl } fullWidth variant="outlined"></TextField>
                </Grid>
                <Grid item sm={3}>
                    <Button type="submit" className={classes.btn} fullWidth variant="contained" color="primary">Cadastrar</Button>
                </Grid>
            </Grid>
        </form>

        <MakeSnackBar/>
        
    </Container>
);
}