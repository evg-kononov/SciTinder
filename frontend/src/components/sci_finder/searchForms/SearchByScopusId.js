import React from "react";
import {Field, reduxForm} from "redux-form";
import {connect} from "react-redux";
import {fetchScientistsByScopusId, authorSearchErrorRemoval} from "../../../actions";
import {menuItems} from "../constants";
import ErrorMessage from "../elements/ErrorMessage";

class SearchByScopusId extends React.Component {
    componentDidMount() {
        if (this.props.search?.error) {
            this.props.authorSearchErrorRemoval(menuItems.scopusId.index);
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
        const code = this.props.onSibmit(formValues, menuItems.scopusId.index);

        if (code !== -1) {
            this.props.fetchScientistsByScopusId(formValues.scopusId);
        }
    }

    renderRequestError() {
        if (!this.props.search?.error) {
            return null;
        }

        return <ErrorMessage
            title="Oooops"
            message={this.props.search?.message}
            onCloseClick={() => this.props.authorSearchErrorRemoval(menuItems.scopusId.index)}
        />;
    }

    render() {
        return (
            <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form error">
                <Field name="scopusId" component={this.renderInput} label="Enter scopus id"/>
                <button className="ui button primary">Find</button>
                {this.renderRequestError()}
            </form>
        );
    }
}

const validate = (formValues) => {
    const errors = {};

    if (!formValues.scopusId) {
        errors.scopusId = 'You must enter an ID';
    } else if (isNaN(Number(formValues.scopusId)) || formValues.scopusId.indexOf(' ') >= 0 || formValues.scopusId === 'Infinity') {
        errors.scopusId = 'ID must be a number';
    }

    return errors;
};

const mapStateToProps = (state) => {
    const search = state.search?.[menuItems.scopusId.index]?.error ? state.search[menuItems.scopusId.index] : false;

    return {
        search: search
    };
};

const formWrapped = reduxForm({
    form: 'searchByScopusId',
    validate
})(SearchByScopusId);

export default connect(
    mapStateToProps,
    {fetchScientistsByScopusId, authorSearchErrorRemoval}
)(formWrapped);