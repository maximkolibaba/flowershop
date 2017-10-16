var ORDER_STATUS_PROCESSING = 'Processing';
var ORDER_STATUS_DELIVERED = 'Delivered';
var BUTTON_VALUE_SHIP = 'Ship';
var BUTTON_VALUE_COMPLETE = 'Complete';

new Vue({
    el: '.layer',
    data: {
        orders: []
    },
    mounted: function () {
        this.$http.get('/rest/admin/orders').then(response => {
            this.orders = response.body;
            for (var i = 0; i < this.orders.length; i++) {
                if (this.orders[i].orderStatus === ORDER_STATUS_PROCESSING) {
                    this.orders[i].buttonValue = BUTTON_VALUE_SHIP;
                } else if (this.orders[i].orderStatus === ORDER_STATUS_DELIVERED) {
                    this.orders[i].buttonValue = BUTTON_VALUE_COMPLETE;
                }
            }
        });
    },
    methods: {
        updateStatus: function (orderId) {
            this.$http.post('/rest/user/update_status', orderId, {
                    headers: {
                        'Content-type': 'text/html'
                    }
                })
                .then(response => {
                    for (var i = 0; i < this.orders.length; i++) {
                        if (this.orders[i].id === response.body.id) {
                            // TODO: cool status sorting
                            this.$set(this.orders, i, response.body);
                            break;
                        }
                    }
                });
        },
        logout: function () {
            // TODO
        }
    }
});