import useStyles from './style';
import { FormControl } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';

export default function Login(){
    const classes = useStyles();

    console.log(classes)
    return(
        <Container maxWidth="sm" className={classes.root}>
            <FormControl fullWidth={true}>
                <TextField id="standard-basic" label="usuário" />
                <TextField id="standard-password-input"
                    label="senha"
                    type="password"
                    autoComplete="current-password"
                    />
                <Button color="primary" className={classes.btnLogin} variant="contained">Entrar</Button>
            </FormControl>
        </Container>
    );
}