import React from "react";
import {connect} from "react-redux";
import {changeSearchMenu} from "../../../actions";

class Menu extends React.Component {
    onMenuClicked = (index) => {
        if (index !== this.props.searchMenuIndex) {
            this.props.changeSearchMenu(index);
        }
    }

    renderMenu() {
        return this.props.menuItems.map((item) => {
            if (item.showInMenuBar) {
                const className = item.index === this.props.searchMenuIndex ? 'item active blue' : 'item';

                return (
                    <React.Fragment key={item.index}>
                        <a className={className} onClick={() => this.onMenuClicked(item.index)}>
                            Search By {item.name}
                        </a>
                    </React.Fragment>
                );
            }
        });
    }

    render() {
        return <div className="ui tabular menu">{this.renderMenu()}</div>;
    }
}

const mapStateToProps = (state) => {
    return {searchMenuIndex: state.searchMenu.searchMenuIndex};
};

export default connect(mapStateToProps, {changeSearchMenu})(Menu);