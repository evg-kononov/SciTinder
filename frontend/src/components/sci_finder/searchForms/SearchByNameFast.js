import React from "react";
import {Field, reduxForm} from "redux-form";
import {connect} from "react-redux";
import {fetchScientistsByNameContaining, authorSearchErrorRemoval} from "../../../actions";
import {menuItems} from "../constants";
import ErrorMessage from "../elements/ErrorMessage";

class SearchByNameFast extends React.Component {
    componentDidMount() {
        if (this.props.search?.error) {
            this.props.authorSearchErrorRemoval(menuItems.fast.index);
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
        const params = {'name': formValues.nameFast}
        const code = this.props.onSibmit(params, menuItems.fast.index);

        if (code !== -1) {
            this.props.fetchScientistsByNameContaining(params);
        }
    }

    renderRequestError() {
        if (!this.props.search?.error) {
            return null;
        }

        return <ErrorMessage
            title="Oooops"
            message={this.props.search?.message}
            onCloseClick={() => this.props.authorSearchErrorRemoval(menuItems.fast.index)}
        />;
    }

    render() {
        return (
            <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form error">
                <Field name="nameFast" component={this.renderInput} label="Enter first name"/>
                <button className="ui button primary">Find</button>
                {this.renderRequestError()}
            </form>
        );
    }
}

const validate = (formValues) => {
    const errors = {};

    if (!formValues.nameFast) {
        errors.nameFast = 'You must enter a name';
    }

    return errors;
};

const mapStateToProps = (state) => {
    const search = state.search?.[menuItems.fast.index]?.error ? state.search[menuItems.fast.index] : false;

    return {
        search: search
    };
};

const formWrapped = reduxForm({
    form: 'searchByNameFast',
    validate
})(SearchByNameFast);

export default connect(
    mapStateToProps,
    {fetchScientistsByNameContaining, authorSearchErrorRemoval}
)(formWrapped);