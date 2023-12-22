import React from "react";
import {Field, reduxForm} from "redux-form";
import {connect} from "react-redux";
import {fetchSimilarByPrompt, changeSearchMenu} from "../../../actions";
import {menuItems} from "../constants";
import SimilaritySearch from "../SimilaritySearch";

class SearchByPrompt extends React.Component {
    componentWillUnmount() {
        this.props.changeSearchMenu(null);
    }

    renderError({error, touched}) {
        if (error && touched) {
            return (
                <div className="ui error message">
                    <div className="header">{error}</div>
                </div>
            );
        }
    }

    renderInput = ({input, placeholder, meta}) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`;
        return (
            <div className={className}>
                <textarea {...input} placeholder={placeholder} rows="10" cols="40"></textarea>
                {this.renderError(meta)}
            </div>
        );
    }

    onSubmit = formValues => {
        const params = {
            "prompt": [formValues.prompt]
        }

        this.props.fetchSimilarByPrompt(params, menuItems.prompt.index);
    }

    render() {
        if (!this.props.searchMenuIndex) {
            this.props.changeSearchMenu(menuItems.prompt.index);
        }

        return (
            <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form">
                <Field name="prompt" component={this.renderInput} placeholder="Enter prompt"/>
                <button className="ui button primary">Find</button>
                <div className="ui hidden divider"/>
                <SimilaritySearch searchMenuIndex={menuItems.prompt.index}/>
            </form>
        );
    }
}

const validate = (formValues) => {
    const errors = {};

    if (!formValues.prompt) {
        errors.prompt = 'You must enter a prompt';
    }

    return errors;
};

const mapStateToProps = (state) => {
    return {
        searchMenuIndex: state.searchMenu.searchMenuIndex
    };
};

const formWrapped = reduxForm({
    form: 'searchByPrompt',
    validate
})(SearchByPrompt);

export default connect(
    mapStateToProps,
    {
    fetchSimilarByPrompt,
    changeSearchMenu
})(formWrapped);