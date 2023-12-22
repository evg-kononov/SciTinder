import React from "react";
import {connect} from "react-redux";
import {changeSimilaritySearchParams} from "../../../actions";

class Checkbox extends React.Component {
    defaultChecked = true;

    componentDidMount() {
        if (this.props.similaritySearchParams?.[this.props.searchMenuIndex]?.checkbox === undefined) {
            this.props.changeSimilaritySearchParams(
                {checkbox: this.defaultChecked},
                this.props.searchMenuIndex
            )
        }
    }

    render() {
        return (
            <div className="ui checkbox">
                <input
                    checked={
                        this.props.similaritySearchParams?.[this.props.searchMenuIndex]?.checkbox === undefined ?
                            this.defaultChecked : this.props.similaritySearchParams[this.props.searchMenuIndex].checkbox
                    }
                    onChange={
                        (event) => this.props.changeSimilaritySearchParams(
                            {checkbox: event.currentTarget.checked},
                            this.props.searchMenuIndex
                        )
                    }
                    type="checkbox"
                />
                <label>{this.props.label}</label>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        searchMenuIndex: state.searchMenu.searchMenuIndex,
        similaritySearchParams: state.similaritySearchParams
    };
};

export default connect(mapStateToProps, {
    changeSimilaritySearchParams
})(Checkbox);