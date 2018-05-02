<?php
class WP_Customize_Control {
	protected static $instance_count = 0;
	public $instance_number;
	public $manager;
	public $id;
	public $settings;
	public $setting = 'default';
	public $capability;
	public $priority = 10;
	public $section = '';
	public $label = '';
	public $description = '';
	public $choices = array();
	public $input_attrs = array();
	public $allow_addition = false;
	public $json = array();
	public $type = 'text';
	public $active_callback = '';
	public function __construct( $manager, $id, $args = array() ) {}
	public function enqueue() {}
	final public function active() {}
	public function active_callback() {}
	final public function value( $setting_key = 'default' ) {}
	public function to_json() {}
	public function json() {}
	final public function check_capabilities() {}
	final public function get_content() {}
	final public function maybe_render() {}
	protected function render() {}
	public function get_link( $setting_key = 'default' ) {}
	public function link( $setting_key = 'default' ) {}
	public function input_attrs() {}
	protected function render_content() {}
	final public function print_template() {}
	protected function content_template() {}
}
