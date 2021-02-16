import React, { useState } from 'react';
import Snackbar from '@material-ui/core/Snackbar';
//import IconButton from '@material-ui/core/IconButton';
import Button from '@material-ui/core/Button';
//import CloseIcon from '@material-ui/icons/Close';

import { useDispatch, useSelector } from 'react-redux';

const MakeSnackBar = (props) => {


    const context = useSelector(state => state);

    const dispatch = useDispatch();

    const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    dispatch({type:'UPDATE', open:false})
    };
console.log(context.updateSnackbar)
    return ( 
    <Snackbar
        open={context.updateSnackbar}
        message={ context.updateMessage }
        anchorOrigin={{vertical:'bottom',horizontal:'left'}}
        action={
          <>
            <Button color="secondary" size="small" onClick={handleClose}>
              UNDO
            </Button>
            
          </>
          }
    >
    </Snackbar>
);
}

export default MakeSnackBar;