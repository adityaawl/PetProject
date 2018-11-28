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

class PetDetail extends React.Component {
	constructor() {
	  super();
	  this.state = {
	    pet: {
	    	name: '', 
	    	birthDate: '', 
	    	ownerId: '', 
	    },
	    owners: [],
	  };

	  this.handleSave = this.handleSave.bind(this);
	  this.handleCancel = this.handleCancel.bind(this);

	}

	handleChange(prop, e) {
	  e.preventDefault();
	  
	  this.setState({
		  pet: {
			  ...this.state.pet,
			  [prop]: e.target.value
		  }
	  });
	  console.log(this.state.pet);
	};


	handleSave(e) {
	  e.preventDefault();
	  console.log(JSON.stringify(this.state.pet))

	  axios.post('/api/create/pet', this.state.pet )
	  	.then(response => {
    	  console.log("Response" + response.data);
    	  alert('Record saved successfully.')
    	  const wrapper = document.getElementById("form-grid-section");
    	  const { entity } = this.props;
    	  wrapper ? ReactDOM.render(<BaseTable entity='Pet' />, wrapper) : false;
	  	})
	  	.catch(error => { 
	  		alert('Operation failed.' + error);
	  	  const wrapper = document.getElementById("form-grid-section");
		  const { entity } = this.props;
		  wrapper ? ReactDOM.render(<BaseTable entity='Pet' />, wrapper) : false;
	  	})
	}

	
	handleCancel(e) {
	  e.preventDefault();
	  const wrapper = document.getElementById("form-grid-section");
	  const { entity } = this.props;
	  wrapper ? ReactDOM.render(<BaseTable entity='Pet' />, wrapper) : false;
	}
	
	componentDidMount() {
		axios.get('/api/owners')
	  	.then(response => {
	  		console.log(response.data);
			this.setState({ owners: response.data})
	  	});
	}
	  
	render() {
		const { classes } = this.props;
		const { entity } = this.props;

		return (
				<div className={classes.root}>
				<form onSubmit={this.handleSave}>
	              <Toolbar className={classes.toolbar}>
	                <div className={classes.toolbarTitle}>
	                  <Typography variant="h5" id="tableTitle">Detail - Pet</Typography>
	                </div>
	                <div className={classes.toolbarSpacer} />
	                <Button color="inherit" variant="outlined" type="submit">Save</Button>
	                <Button color="inherit" variant="outlined" onClick={this.handleCancel}>Cancel</Button>
	              </Toolbar>
	              <div className={classes.detailSection}>
	              <TextField
	                label="Name"
	                id="name"
	                required={true}
	                className={classes.textField}
	                onChange={this.handleChange.bind(this, 'name')}
	                InputLabelProps={{
	                  shrink: true,
	                }}
	              />
	              <TextField
	                label="Birth date"
	                id="birthDate"
	                type="date"
	                className={classes.textField}
	                onChange={this.handleChange.bind(this, 'birthDate')}
	                InputLabelProps={{
	                  shrink: true,
	                }}
	              />
	              <TextField
	                select
	                label="Owner"
	                id="ownerId"
	                className={classNames(classes.textField, classes.menuItem)}
	                value={this.state.pet.ownerId}
	                onChange={this.handleChange.bind(this, 'ownerId')}
	              	required
	              	SelectProps={{
	              		native: true,
	              	}}
	              	InputLabelProps={{ 
	              		shrink: true,
	                }}
	              >
		            <option value="" />
		            {this.state.owners.map(option => (
		            		<option value={option.id}> 
			                  {option.lastName}, {option.firstName}
			                </option>
			                ))}
	              </TextField>

	              </div>
	            </form>
	            </div>
	  );
	}
}

PetDetail.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PetDetail);
