import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles( theme => ({
    root:{
      backgroundColor: theme.palette.background.paper,
      marginTop:50
    },
    btnLogin:{
        marginTop:50,
        marginBottom:20
    }
}));

export default useStyles;