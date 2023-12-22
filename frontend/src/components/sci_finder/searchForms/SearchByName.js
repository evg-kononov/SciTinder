import React from "react";
import {Field, reduxForm} from "redux-form";
import {connect} from "react-redux";
import {fetchScientistsByNameLike, authorSearchErrorRemoval} from "../../../actions";
import {menuItems} from "../constants";
import ErrorMessage from "../elements/ErrorMessage";

class SearchByName extends React.Component {
    componentDidMount() {
        if (this.props.search?.error) {
            this.props.authorSearchErrorRemoval(menuItems.name.index);
        }
    }

    renderError({error, touched, submitFailed}) {
        if (error && touched && submitFailed) {
            return (
                <div className="ui error message">
                    <div className="header">{error}</div>
                </div>
            );
        }
    }

    renderInput = ({input, label, meta}) => {
        const className = `field ${meta.error && meta.touched && meta.submitFailed ? 'error' : ''}`;
        return (
            <div className={className}>
                <label>
                    {label}
                    <input {...input}/>
                    {this.renderError(meta)}
                </label>
            </div>
        );
    }

    onSubmit = formValues => {
        const code = this.props.onSibmit(formValues, menuItems.name.index);

        if (code !== -1) {
            this.props.fetchScientistsByNameLike(formValues);
        }
    }

    renderRequestError() {
        if (!this.props.search?.error) {
            return null;
        }

        return <ErrorMessage
            title="Oooops"
            message={this.props.search?.message}
            onCloseClick={() => this.props.authorSearchErrorRemoval(menuItems.name.index)}
        />;
    }

    render() {
        return (
            <div>
                <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form error">
                    <Field name="firstname" component={this.renderInput} label="Enter first name"/>
                    <Field name="lastname" component={this.renderInput} label="Enter last name"/>
                    <button className="ui button primary">Find</button>
                    {this.renderRequestError()}
                </form>
            </div>
        );
    }
}

const validate = (formValues) => {
    const errors = {};

    if (!formValues.firstname) {
        errors.firstname = 'You must enter a first name';
    }

    if (!formValues.lastname) {
        errors.lastname = 'You must enter a last name';
    }

    return errors;
};

const mapStateToProps = (state) => {
    // const search = state.search?.[menuItems.name.index]?.error ? state.search[menuItems.name.index] : null;

    return {
        search: state.search[menuItems.name.index]
    };
};

const formWrapped = reduxForm({
    form: 'searchByName',
    validate
})(SearchByName);

export default connect(
    mapStateToProps,
    {
        fetchScientistsByNameLike,
        authorSearchErrorRemoval
    })(formWrapped);