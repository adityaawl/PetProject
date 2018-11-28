import React, { Component } from "react";
import ReactDOM from "react-dom";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import axios from "axios";

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

const CustomTableCell = withStyles(theme => ({
	  head: {
	    backgroundColor: theme.palette.common.black,
	    color: theme.palette.common.white,
	  },
	  body: {
	    fontSize: 14,
	  },
	}))(TableCell);

const styles = theme => ({
	  table: {
	    minWidth: 600,
	  },
	  row: {
	    '&:nth-of-type(odd)': {
	      backgroundColor: theme.palette.background.default,
	    },
	  },
	});

class PetTable extends Component {
  constructor() {
    super();
    this.state = {
      
    };
  }

  render() {
	  const { classes } = this.props;
	  const { pets } = this.props;
	  
	  return (
    	<div>
    		<Table className={classes.table}>
	            <TableHead>
	              <TableRow>
	                <CustomTableCell>Name</CustomTableCell>
	                <CustomTableCell>BirthDate</CustomTableCell>
	                <CustomTableCell>Owner</CustomTableCell>
	              </TableRow>
	            </TableHead>
	            <TableBody>
	              {pets.map(row => {
	                return (
	                  <TableRow className={classes.row} key={row.id}>
	                    <CustomTableCell component="th" scope="row">{row.name}</CustomTableCell>
	                    <CustomTableCell>{row.birthDate}</CustomTableCell>
	                    <CustomTableCell>{row.ownerId}</CustomTableCell>
	                  </TableRow>
	                );
	              })}
	            </TableBody>
	        </Table>
	     </div>
    );
  }
}

PetTable.propTypes = {
		  classes: PropTypes.object.isRequired,
		};

export default withStyles(styles)(PetTable);
