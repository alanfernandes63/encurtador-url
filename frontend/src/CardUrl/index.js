import Card from '@material-ui/core/Card';
import Grid from '@material-ui/core/Grid';
import style from './style';

const CarUrl = (props) => {

    const classes = style();

    const calcularDivisor = (url) => {
        return url.length * 0.03;
    }

    const dividirUrl = (url) => {

        const urlQuebrada = [];

        const corte = url.length / Math.trunc(calcularDivisor(url));
        for(let i= 0; i <= url.length; i += corte){
            urlQuebrada.push(url.slice(i, i + corte));
        }
        return urlQuebrada;
    }

    return (
        <Grid key={props.id} xs={12} sm={4} item>
            <Card className={classes.card}>
                {dividirUrl(props.conteudo).map(
                    urlDividida => (<div key={Math.random()}><code>{ urlDividida }</code><br></br></div>)
                )}
            </Card>
        </Grid>
    );
}

export default CarUrl;