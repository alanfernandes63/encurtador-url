import Container from '@material-ui/core/Container';
import useStyles from './style';
import Grid from '@material-ui/core/Grid';
import { useEffect } from 'react';
import CardUrl from '../CardUrl/index';
import Card from '@material-ui/core/Card';
import { useDispatch, useSelector } from 'react-redux';
import cliente from '../axios';
import { ADD_ALL, ADD } from '../redux/actions';
import store from '../redux/reducer';
import { useDispatch, useSelector } from 'react-redux';

const UrlList = function(){

    const context = useSelector(state => state);

    const dispatch = useDispatch();
    
    useEffect(() =>{
        cliente.get('/url/usuario/1')
        .then(function (response) {
        store.dispatch({type:ADD_ALL, urls:response.data.payload});
    });
    }, [dispatch]);

    const classes = useStyles();

    const formatter = new Intl.DateTimeFormat('pt-BR', {timeZone: 'UTC'});

    return(
        <Container >
        {
            context.urlReducer.urls.map(
                url => (
                <Grid container spacing={2} key={url.id}>
                    <CardUrl conteudo={url.urlOriginal}/>
                    <CardUrl conteudo={url.urlCurta}/>
                    <Grid item xs={12} sm={4}>
                        <Card className={classes.data}>{formatter.format(new Date(url.localDate))}</Card>
                    </Grid>
                </Grid>
                )
            )
        }
        </Container>
    );
}

export default UrlList;