import _ from "lodash";
import React from "react";
import {connect} from "react-redux";
import {changeSearchParams, errorActionCreator} from "../../../actions";
import SearchByName from "../searchForms/SearchByName";
import SearchByScopusId from "../searchForms/SearchByScopusId";
import SearchByNameFast from "../searchForms/SearchByNameFast";
import {menuItems} from "../constants";

class Input extends React.Component {
    onSubmit = (requestParams, searchMenuIndex) => {
        if (_.isEqual(this.props.searchParams?.[searchMenuIndex]?.requestParams, requestParams)) {
            if (!this.props.search[searchMenuIndex]) {
                this.props.errorActionCreator(
                    _.mapKeys(menuItems, 'index')[searchMenuIndex].fetchScientistsErrorAction,
                    {message: 'Try something else'},
                    searchMenuIndex
                );
            }
            return -1;
        }

        this.props.changeSearchParams({
            requestParams: requestParams,
            searchMenuIndex: searchMenuIndex
        });
    }

    render() {
        switch (this.props.searchMenuIndex) {
            case 0:
                return <SearchByName onSibmit={this.onSubmit}/>;
            case 1:
                return <SearchByScopusId onSibmit={this.onSubmit}/>;
            case 2:
                return <SearchByNameFast onSibmit={this.onSubmit}/>
            default:
                return null;
        }
    }
}

const mapStateToProps = (state) => {
    return {
        searchMenuIndex: state.searchMenu.searchMenuIndex,
        searchParams: state.searchParams,
        search: state.search
    };
};

export default connect(mapStateToProps, {
    changeSearchParams,
    errorActionCreator
})(Input);