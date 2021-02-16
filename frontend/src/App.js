import React from 'react';
import { Provider } from 'react-redux'
import { ThemeProvider } from '@material-ui/core/styles';
import { CssBaseline } from "@material-ui/core";
import darkTheme from './Theme';
import Login from './Login/index';
import UrlList from './UrlList/index';
import UrlCadastro from './UrlCadastro/index';

import store from './redux/reducer';

function App() {

  return (
    <Provider store={store}>
     <ThemeProvider theme={ darkTheme }>
       <CssBaseline>
          <UrlCadastro/>
          <UrlList/>
       </CssBaseline>
     </ThemeProvider>
     </Provider>
  );
}

export default App;
