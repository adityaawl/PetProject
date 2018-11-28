import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import axios from "axios";

import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';

import OwnerTable from "./OwnerTable";
import OwnerDetail from "./OwnerDetail";
import PetTable from "./PetTable";
import PetDetail from "./PetDetail";

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 2,
    overflowX: 'auto',
    textAlign: 'center',
    // paddingTop: theme.spacing.unit * 10,
  },
  tableToolbar: {
    paddingRight: theme.spacing.unit,
  },
  tableToolbarSpacer: {
    flex: '1 1 100%',
  },
  tableTitle: {
    flex: '0 0 auto',
  },
});

class BaseTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    		owners: [],
    		pets: []
    };
  }

  handleClick(e) {
    e.preventDefault();
    const wrapper = document.getElementById("form-grid-section");
    const { entity } = this.props;
	if(entity == 'Owner') {
		wrapper ? ReactDOM.render(<OwnerDetail />, wrapper) : false;
	}
	else {
		axios.get('/api/owners')
		.then(response => {
			console.log(response.data);
			this.setState({ owners: response.data})
		});
		wrapper ? ReactDOM.render(<PetDetail />, wrapper) : false;
	}
  }

  componentDidMount() {
	const { entity } = this.props;
	if(entity == 'Owner') {
		axios.get('/api/owners')
			.then(response => {
				console.log(response.data);
				this.setState({ owners: response.data})
			});
	}
	else {
		axios.get('/api/pets')
		.then(response => {
			console.log(response.data);
			this.setState({ pets: response.data})
		});
	}
  }
  
  componentDidUpdate(prevProps) {
	  if (this.props.entity !== prevProps.entity) {
		  const { entity } = this.props;
			if(entity == 'Owner') {
				axios.get('/api/owners')
					.then(response => {
						console.log(response.data);
						this.setState({ owners: response.data})
					});
			}
			else {
				axios.get('/api/pets')
				.then(response => {
					console.log(response.data);
					this.setState({ pets: response.data})
				});
			}
	  }
	}
  
  render() {
    const { classes } = this.props;
    const { entity } = this.props;
    const renderTable = entity => {
    	const wrapper = document.getElementById("table-section");
    	if( entity == 'Owner')
    		wrapper ? ReactDOM.render(<OwnerTable owners={this.state.owners}/>, wrapper) : false;	
    	else
    		wrapper ? ReactDOM.render(<PetTable pets={this.state.pets}/>, wrapper) : false;
    };

    return (
      <div className={classes.root}>
        <Toolbar className={classes.tableToolbar}>
          <div className={classes.tableTitle}>
            <Typography variant="h5" id="tableTitle">{entity}</Typography>
          </div>
          <div className={classes.tableToolbarSpacer} />
          <Button color="inherit" variant="outlined" onClick={this.handleClick.bind(this)}>Add</Button>
        </Toolbar>
        <Paper className={classes.root}>
        	<div id='table-section'>
        		{renderTable(entity)}
         	</div>
        </Paper>
      </div>
    );
  }
}

BaseTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BaseTable);