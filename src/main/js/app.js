
import React, { Component } from "react";
import ReactDOM from "react-dom";
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';

import AppRoot from "./pages/AppRoot";

const theme = createMuiTheme();

function App() {
  return (
    <MuiThemeProvider theme={theme}>
      <AppRoot />
    </MuiThemeProvider>
  );
}

ReactDOM.render( <App />, document.getElementById('react-form') )
