import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

import MenuList from '@material-ui/core/MenuList';
import MenuItem from '@material-ui/core/MenuItem';
import ListItemText from '@material-ui/core/ListItemText';

import BaseTable from "./BaseTable";

const styles = theme => ({
  grow: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing.unit * 1,
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
  menuItem: {
    '&:focus': {
      backgroundColor: theme.palette.primary.main,
      '& $primary': {
        color: theme.palette.common.white,
      },
    },
  },
});

class AppRoot extends React.Component {
  constructor() {
    super();
  }

  handleClick(id, e) {
    e.preventDefault();
    const wrapper = document.getElementById("form-grid-section");
    if ('owner' === id) {
      wrapper ? ReactDOM.render(<BaseTable entity='Owner'/>, wrapper) : false;
    }
    else {
      wrapper ? ReactDOM.render(<BaseTable entity='Pet'/>, wrapper) : false;
    }
  }

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.grow}>
        <Grid container spacing={24}>
          <Grid item xs={12}>
            <Paper className={classes.paper}>
                <AppBar position="static">
                  <Toolbar>
                    <Typography variant="h4" color="inherit" className={classes.grow}>Pet Project</Typography>
                  </Toolbar>
                </AppBar>
            </Paper>
          </Grid>
          <Grid item xs={2}>
            <Paper className={classes.paper}>
                <MenuList >
                  <MenuItem className={classes.menuItem} onClick={this.handleClick.bind(this, 'owner')}>
                    <ListItemText classes={{ primary: classes.primary }} primary="Owner" />
                  </MenuItem>
                  <MenuItem className={classes.menuItem} onClick={this.handleClick.bind(this, 'pet')}>
                    <ListItemText classes={{ primary: classes.primary }} primary="Pet" />
                  </MenuItem>
                </MenuList>
            </Paper>
          </Grid>
          <Grid item xs={10}>
            <Paper className={classes.paper}>
              <div id='form-grid-section'>
              	<BaseTable entity='Owner'/>
              </div>
            </Paper>
          </Grid>
        </Grid>
      </div>
    );
  }
}

AppRoot.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(AppRoot)