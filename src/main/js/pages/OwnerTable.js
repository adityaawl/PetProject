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

class OwnerTable extends Component {
  constructor() {
    super();
    this.state = {
      
    };
  }

  render() {
	  const { classes } = this.props;
	  const { owners } = this.props;
	  
	  return (
    	<div>
    		<Table className={classes.table}>
	            <TableHead>
	              <TableRow>
	                <CustomTableCell>First Name</CustomTableCell>
	                <CustomTableCell>Last Name</CustomTableCell>
	                <CustomTableCell>City</CustomTableCell>
	                <CustomTableCell>Pet(s)</CustomTableCell>
	              </TableRow>
	            </TableHead>
	            <TableBody>
	              {owners.map(row => {
	                return (
	                  <TableRow className={classes.row} key={row.id}>
	                    <CustomTableCell component="th" scope="row">{row.firstName}</CustomTableCell>
	                    <CustomTableCell>{row.lastName}</CustomTableCell>
	                    <CustomTableCell>{row.city}</CustomTableCell>
	                    <CustomTableCell>{JSON.stringify(row.petIds)}</CustomTableCell>
	                  </TableRow>
	                );
	              })}
	            </TableBody>
	        </Table>
	     </div>
    );
  }
}

OwnerTable.propTypes = {
		  classes: PropTypes.object.isRequired,
		};

export default withStyles(styles)(OwnerTable);
