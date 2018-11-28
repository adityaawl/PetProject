import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';

import axios from "axios";

import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

import Input from '@material-ui/core/Input';
import TextField from '@material-ui/core/TextField';
import InputLabel from '@material-ui/core/InputLabel';

import BaseTable from "./BaseTable";

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 2,
    overflowX: 'auto',
    textAlign: 'center',
  },
  toolbar: {
    paddingRight: theme.spacing.unit,
  },
  toolbarSpacer: {
    flex: '1 1 100%',
  },
  toolbarTitle: {
    flex: '0 0 auto',
  },
  detailSection: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    margin: theme.spacing.unit,
    flexBasis: 250,
  },
  menuItem: {
      textAlign: 'left',
  }
});

class OwnerDetail extends React.Component {
	constructor() {
	  super();
	  this.state = {
	    owner: {
	    	firstName: '', 
	    	lastName: '', 
	    	city: '', 
	    }
	  };

	  this.handleSave = this.handleSave.bind(this);
	  this.handleCancel = this.handleCancel.bind(this);

	}

	handleChange(prop, e) {
	  e.preventDefault();
	  
	  this.setState({
		  owner: {
			  ...this.state.owner,
			  [prop]: e.target.value
		  }
	  });
	  console.log(this.state.owner);
	};


	handleSave(e) {
	  e.preventDefault();
	  console.log(JSON.stringify(this.state.owner))

	  axios.post('/api/create/owner', this.state.owner )
	  	.then(response => {
    	  console.log("Response" + response.data);
    	  alert('Record saved successfully.')
    	  const wrapper = document.getElementById("form-grid-section");
    	  const { entity } = this.props;
    	  wrapper ? ReactDOM.render(<BaseTable entity='Owner' />, wrapper) : false;
	  	})
	  	.catch(error => { 
	  		alert('Operation failed.' + error);
	  	  const wrapper = document.getElementById("form-grid-section");
		  const { entity } = this.props;
		  wrapper ? ReactDOM.render(<BaseTable entity='Owner' />, wrapper) : false;
	  	})
	}

	handleCancel(e) {
	  e.preventDefault();
	  const wrapper = document.getElementById("form-grid-section");
	  const { entity } = this.props;
	  wrapper ? ReactDOM.render(<BaseTable entity='Owner' />, wrapper) : false;
	}
		  
	render() {
		const { classes } = this.props;
		const { entity } = this.props;

		return (
				<div className={classes.root}>
				<form onSubmit={this.handleSave}>
	              <Toolbar className={classes.toolbar}>
	                <div className={classes.toolbarTitle}>
	                  <Typography variant="h5" id="tableTitle">Detail - Owner</Typography>
	                </div>
	                <div className={classes.toolbarSpacer} />
	                <Button color="inherit" variant="outlined" type="submit">Save</Button>
	                <Button color="inherit" variant="outlined" onClick={this.handleCancel}>Cancel</Button>
	              </Toolbar>
	              <div className={classes.detailSection}>
	              <TextField
	                label="First Name"
	                id="firstName"
	                required={true}
	                className={classes.textField}
	                onChange={this.handleChange.bind(this, 'firstName')}
	                InputLabelProps={{
	                  shrink: true,
	                }}
	              />
	              <TextField
	                label="Last Name"
	                id="lastName"
	                required={true}
	                className={classes.textField}
	                onChange={this.handleChange.bind(this, 'lastName')}
	                InputLabelProps={{
	                  shrink: true,
	                }}
	              />
	              <TextField
	                label="City"
	                id="city"
	                className={classNames(classes.textField, classes.menuItem)}
	                value={this.state.owner.ownerId}
	                onChange={this.handleChange.bind(this, 'city')}
	                InputLabelProps={{
	                  shrink: true,
	                }}
	              />
	              
	              </div>
	            </form>
	            </div>
	  );
	}
}

OwnerDetail.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OwnerDetail);
