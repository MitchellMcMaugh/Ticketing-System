<?php
final class WP_Customize_Manager {
	protected $theme;
	protected $original_stylesheet;
	protected $previewing = false;
	public $widgets;
	public $nav_menus;
	public $selective_refresh;
	protected $settings = array();
	protected $containers = array();
	protected $panels = array();
	protected $components = array( 'widgets', 'nav_menus' );
	protected $sections = array();
	protected $controls = array();
	protected $registered_panel_types = array();
	protected $registered_section_types = array();
	protected $registered_control_types = array();
	protected $preview_url;
	protected $return_url;
	protected $autofocus = array();
	protected $messenger_channel;
	private $_post_values;
	private $_changeset_uuid;
	private $_changeset_post_id;
	private $_changeset_data;
	public function __construct( $args = array() ) {}
	public function doing_ajax( $action = null ) {}
	protected function wp_die( $ajax_message, $message = null ) {}
	public function wp_die_handler() {}
	public function setup_theme() {}
	public function after_setup_theme() {}
	public function start_previewing_theme() {}
	public function stop_previewing_theme() {}
	public function changeset_uuid() {}
	public function theme() {}
	public function settings() {}
	public function controls() {}
	public function containers() {}
	public function sections() {}
	public function panels() {}
	public function is_theme_active() {}
	public function wp_loaded() {}
	public function wp_redirect_status( $status ) {}
	public function find_changeset_post_id( $uuid ) {}
	public function changeset_post_id() {}
	protected function get_changeset_post_data( $post_id ) {}
	public function changeset_data() {}
	protected $pending_starter_content_settings_ids = array();
	function import_theme_starter_content( $starter_content = array() ) {}
	protected function prepare_starter_content_attachments( $attachments ) {}
	public function _save_starter_content_changeset() {}
	public function unsanitized_post_values( $args = array() ) {}
	public function post_value( $setting, $default = null ) {}
	public function set_post_value( $setting_id, $value ) {}
	public function customize_preview_init() {}
	public function filter_iframe_security_headers( $headers ) {}
	public function add_state_query_params( $url ) {}
	public function customize_preview_override_404_status() {}
	public function customize_preview_base() {}
	public function customize_preview_html5() {}
	public function customize_preview_loading_style() {}
	public function remove_frameless_preview_messenger_channel() {}
	public function customize_preview_settings() {}
	public function customize_preview_signature() {}
	public function remove_preview_signature( $return = null ) {}
	public function is_preview() {}
	public function get_template() {}
	public function get_stylesheet() {}
	public function get_template_root() {}
	public function get_stylesheet_root() {}
	public function current_theme( $current_theme ) {}
	public function validate_setting_values( $setting_values, $options = array() ) {}
	public function prepare_setting_validity_for_js( $validity ) {}
	public function save() {}
	function save_changeset_post( $args = array() ) {}
	protected $store_changeset_revision;
	public function _filter_revision_post_has_changed( $post_has_changed, $last_revision, $post ) {}
	public function _publish_changeset_values( $changeset_post_id ) {}
	protected function update_stashed_theme_mod_settings( $inactive_theme_mod_settings ) {}
	public function refresh_nonces() {}
	public function add_setting( $id, $args = array() ) {}
	public function add_dynamic_settings( $setting_ids ) {}
	public function get_setting( $id ) {}
	public function remove_setting( $id ) {}
	public function add_panel( $id, $args = array() ) {}
	public function get_panel( $id ) {}
	public function remove_panel( $id ) {}
	public function register_panel_type( $panel ) {}
	public function render_panel_templates() {}
	public function add_section( $id, $args = array() ) {}
	public function get_section( $id ) {}
	public function remove_section( $id ) {}
	public function register_section_type( $section ) {}
	public function render_section_templates() {}
	public function add_control( $id, $args = array() ) {}
	public function get_control( $id ) {}
	public function remove_control( $id ) {}
	public function register_control_type( $control ) {}
	public function render_control_templates() {}
	protected function _cmp_priority( $a, $b ) {}
	public function prepare_controls() {}
	public function enqueue_control_scripts() {}
	public function is_ios() {}
	public function get_document_title_template() {}
	public function set_preview_url( $preview_url ) {}
	public function get_preview_url() {}
	public function is_cross_domain() {}
	public function get_allowed_urls() {}
	public function get_messenger_channel() {}
	public function set_return_url( $return_url ) {}
	public function get_return_url() {}
	public function set_autofocus( $autofocus ) {}
	public function get_autofocus() {}
	public function get_nonces() {}
	public function customize_pane_settings() {}
	public function get_previewable_devices() {}
	public function register_controls() {}
	public function has_published_pages() {}
	public function register_dynamic_settings() {}
	public function _sanitize_header_textcolor( $color ) {}
	public function _sanitize_background_setting( $value, $setting ) {}
	public function export_header_video_settings( $response, $selective_refresh, $partials ) {}
	public function _validate_header_video( $validity, $value ) {}
	public function _validate_external_header_video( $validity, $value ) {}
	public function _sanitize_external_header_video( $value ) {}
	public function _render_custom_logo_partial() {}
}
