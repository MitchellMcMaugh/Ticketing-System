<?php
/**
 * HBA Intranet Theme Customizer functionality
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

/**
 * Sets up the WordPress core custom header and custom background features.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see hbaintranettheme_header_style()
 */
function hbaintranettheme_custom_header_and_background() {
	$color_scheme             = hbaintranettheme_get_color_scheme();
	$default_background_color = trim( $color_scheme[0], '#' );
	$default_text_color       = trim( $color_scheme[3], '#' );

	/**
	 * Filter the arguments used when adding 'custom-background' support in HBA Intranet Theme.
	 *
	 * @since HBA Intranet Theme 1.0
	 *
	 * @param array $args {
	 *     An array of custom-background support arguments.
	 *
	 *     @type string $default-color Default color of the background.
	 * }
	 */
	add_theme_support( 'custom-background', apply_filters( 'hbaintranettheme_custom_background_args', array(
		'default-color' => $default_background_color,
	) ) );

	/**
	 * Filter the arguments used when adding 'custom-header' support in HBA Intranet Theme.
	 *
	 * @since HBA Intranet Theme 1.0
	 *
	 * @param array $args {
	 *     An array of custom-header support arguments.
	 *
	 *     @type string $default-text-color Default color of the header text.
	 *     @type int      $width            Width in pixels of the custom header image. Default 1200.
	 *     @type int      $height           Height in pixels of the custom header image. Default 280.
	 *     @type bool     $flex-height      Whether to allow flexible-height header images. Default true.
	 *     @type callable $wp-head-callback Callback function used to style the header image and text
	 *                                      displayed on the blog.
	 * }
	 */
	add_theme_support( 'custom-header', apply_filters( 'hbaintranettheme_custom_header_args', array(
		'default-text-color'     => $default_text_color,
		'width'                  => 1200,
		'height'                 => 280,
		'flex-height'            => true,
		'wp-head-callback'       => 'hbaintranettheme_header_style',
	) ) );
}
add_action( 'after_setup_theme', 'hbaintranettheme_custom_header_and_background' );

if ( ! function_exists( 'hbaintranettheme_header_style' ) ) :
/**
 * Styles the header text displayed on the site.
 *
 * Create your own hbaintranettheme_header_style() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see hbaintranettheme_custom_header_and_background().
 */
function hbaintranettheme_header_style() {
	// If the header text option is untouched, let's bail.
	if ( display_header_text() ) {
		return;
	}

	// If the header text has been hidden.
	?>
	<style type="text/css" id="hbaintranettheme-header-css">
		.site-branding {
			margin: 0 auto 0 0;
		}

		.site-branding .site-title,
		.site-description {
			clip: rect(1px, 1px, 1px, 1px);
			position: absolute;
		}
	</style>
	<?php
}
endif; // hbaintranettheme_header_style

/**
 * Adds postMessage support for site title and description for the Customizer.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @param WP_Customize_Manager $wp_customize The Customizer object.
 */
function hbaintranettheme_customize_register( $wp_customize ) {
	$color_scheme = hbaintranettheme_get_color_scheme();

	$wp_customize->get_setting( 'blogname' )->transport         = 'postMessage';
	$wp_customize->get_setting( 'blogdescription' )->transport  = 'postMessage';

	// Add color scheme setting and control.
	$wp_customize->add_setting( 'color_scheme', array(
		'default'           => 'default',
		'sanitize_callback' => 'hbaintranettheme_sanitize_color_scheme',
		'transport'         => 'postMessage',
	) );

	$wp_customize->add_control( 'color_scheme', array(
		'label'    => __( 'Base Color Scheme', 'hbaintranettheme' ),
		'section'  => 'colors',
		'type'     => 'select',
		'choices'  => hbaintranettheme_get_color_scheme_choices(),
		'priority' => 1,
	) );

	// Add page background color setting and control.
	$wp_customize->add_setting( 'page_background_color', array(
		'default'           => $color_scheme[1],
		'sanitize_callback' => 'sanitize_hex_color',
		'transport'         => 'postMessage',
	) );

	$wp_customize->add_control( new WP_Customize_Color_Control( $wp_customize, 'page_background_color', array(
		'label'       => __( 'Page Background Color', 'hbaintranettheme' ),
		'section'     => 'colors',
	) ) );

	// Remove the core header textcolor control, as it shares the main text color.
	$wp_customize->remove_control( 'header_textcolor' );

	// Add link color setting and control.
	$wp_customize->add_setting( 'link_color', array(
		'default'           => $color_scheme[2],
		'sanitize_callback' => 'sanitize_hex_color',
		'transport'         => 'postMessage',
	) );

	$wp_customize->add_control( new WP_Customize_Color_Control( $wp_customize, 'link_color', array(
		'label'       => __( 'Link Color', 'hbaintranettheme' ),
		'section'     => 'colors',
	) ) );

	// Add main text color setting and control.
	$wp_customize->add_setting( 'main_text_color', array(
		'default'           => $color_scheme[3],
		'sanitize_callback' => 'sanitize_hex_color',
		'transport'         => 'postMessage',
	) );

	$wp_customize->add_control( new WP_Customize_Color_Control( $wp_customize, 'main_text_color', array(
		'label'       => __( 'Main Text Color', 'hbaintranettheme' ),
		'section'     => 'colors',
	) ) );

	// Add secondary text color setting and control.
	$wp_customize->add_setting( 'secondary_text_color', array(
		'default'           => $color_scheme[4],
		'sanitize_callback' => 'sanitize_hex_color',
		'transport'         => 'postMessage',
	) );

	$wp_customize->add_control( new WP_Customize_Color_Control( $wp_customize, 'secondary_text_color', array(
		'label'       => __( 'Secondary Text Color', 'hbaintranettheme' ),
		'section'     => 'colors',
	) ) );
}
add_action( 'customize_register', 'hbaintranettheme_customize_register', 11 );

/**
 * Registers color schemes for HBA Intranet Theme.
 *
 * Can be filtered with {@see 'hbaintranettheme_color_schemes'}.
 *
 * The order of colors in a colors array:
 * 1. Main Background Color.
 * 2. Page Background Color.
 * 3. Link Color.
 * 4. Main Text Color.
 * 5. Secondary Text Color.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @return array An associative array of color scheme options.
 */
function hbaintranettheme_get_color_schemes() {
	/**
	 * Filter the color schemes registered for use with HBA Intranet Theme.
	 *
	 * The default schemes include 'default', 'dark', 'gray', 'red', and 'yellow'.
	 *
	 * @since HBA Intranet Theme 1.0
	 *
	 * @param array $schemes {
	 *     Associative array of color schemes data.
	 *
	 *     @type array $slug {
	 *         Associative array of information for setting up the color scheme.
	 *
	 *         @type string $label  Color scheme label.
	 *         @type array  $colors HEX codes for default colors prepended with a hash symbol ('#').
	 *                              Colors are defined in the following order: Main background, page
	 *                              background, link, main text, secondary text.
	 *     }
	 * }
	 */
	return apply_filters( 'hbaintranettheme_color_schemes', array(
		'default' => array(
			'label'  => __( 'Default', 'hbaintranettheme' ),
			'colors' => array(
				'#1a1a1a',
				'#ffffff',
				'#007acc',
				'#1a1a1a',
				'#686868',
			),
		),
		'dark' => array(
			'label'  => __( 'Dark', 'hbaintranettheme' ),
			'colors' => array(
				'#262626',
				'#1a1a1a',
				'#9adffd',
				'#e5e5e5',
				'#c1c1c1',
			),
		),
		'gray' => array(
			'label'  => __( 'Gray', 'hbaintranettheme' ),
			'colors' => array(
				'#616a73',
				'#4d545c',
				'#c7c7c7',
				'#f2f2f2',
				'#f2f2f2',
			),
		),
		'red' => array(
			'label'  => __( 'Red', 'hbaintranettheme' ),
			'colors' => array(
				'#ffffff',
				'#ff675f',
				'#640c1f',
				'#402b30',
				'#402b30',
			),
		),
		'yellow' => array(
			'label'  => __( 'Yellow', 'hbaintranettheme' ),
			'colors' => array(
				'#3b3721',
				'#ffef8e',
				'#774e24',
				'#3b3721',
				'#5b4d3e',
			),
		),
	) );
}

if ( ! function_exists( 'hbaintranettheme_get_color_scheme' ) ) :
/**
 * Retrieves the current HBA Intranet Theme color scheme.
 *
 * Create your own hbaintranettheme_get_color_scheme() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @return array An associative array of either the current or default color scheme HEX values.
 */
function hbaintranettheme_get_color_scheme() {
	$color_scheme_option = get_theme_mod( 'color_scheme', 'default' );
	$color_schemes       = hbaintranettheme_get_color_schemes();

	if ( array_key_exists( $color_scheme_option, $color_schemes ) ) {
		return $color_schemes[ $color_scheme_option ]['colors'];
	}

	return $color_schemes['default']['colors'];
}
endif; // hbaintranettheme_get_color_scheme

if ( ! function_exists( 'hbaintranettheme_get_color_scheme_choices' ) ) :
/**
 * Retrieves an array of color scheme choices registered for HBA Intranet Theme.
 *
 * Create your own hbaintranettheme_get_color_scheme_choices() function to override
 * in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @return array Array of color schemes.
 */
function hbaintranettheme_get_color_scheme_choices() {
	$color_schemes                = hbaintranettheme_get_color_schemes();
	$color_scheme_control_options = array();

	foreach ( $color_schemes as $color_scheme => $value ) {
		$color_scheme_control_options[ $color_scheme ] = $value['label'];
	}

	return $color_scheme_control_options;
}
endif; // hbaintranettheme_get_color_scheme_choices


if ( ! function_exists( 'hbaintranettheme_sanitize_color_scheme' ) ) :
/**
 * Handles sanitization for HBA Intranet Theme color schemes.
 *
 * Create your own hbaintranettheme_sanitize_color_scheme() function to override
 * in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @param string $value Color scheme name value.
 * @return string Color scheme name.
 */
function hbaintranettheme_sanitize_color_scheme( $value ) {
	$color_schemes = hbaintranettheme_get_color_scheme_choices();

	if ( ! array_key_exists( $value, $color_schemes ) ) {
		return 'default';
	}

	return $value;
}
endif; // hbaintranettheme_sanitize_color_scheme

/**
 * Enqueues front-end CSS for color scheme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see wp_add_inline_style()
 */
function hbaintranettheme_color_scheme_css() {
	$color_scheme_option = get_theme_mod( 'color_scheme', 'default' );

	// Don't do anything if the default color scheme is selected.
	if ( 'default' === $color_scheme_option ) {
		return;
	}

	$color_scheme = hbaintranettheme_get_color_scheme();

	// Convert main text hex color to rgba.
	$color_textcolor_rgb = hbaintranettheme_hex2rgb( $color_scheme[3] );

	// If the rgba values are empty return early.
	if ( empty( $color_textcolor_rgb ) ) {
		return;
	}

	// If we get this far, we have a custom color scheme.
	$colors = array(
		'background_color'      => $color_scheme[0],
		'page_background_color' => $color_scheme[1],
		'link_color'            => $color_scheme[2],
		'main_text_color'       => $color_scheme[3],
		'secondary_text_color'  => $color_scheme[4],
		'border_color'          => vsprintf( 'rgba( %1$s, %2$s, %3$s, 0.2)', $color_textcolor_rgb ),

	);

	$color_scheme_css = hbaintranettheme_get_color_scheme_css( $colors );

	wp_add_inline_style( 'hbaintranettheme-style', $color_scheme_css );
}
add_action( 'wp_enqueue_scripts', 'hbaintranettheme_color_scheme_css' );

/**
 * Binds the JS listener to make Customizer color_scheme control.
 *
 * Passes color scheme data as colorScheme global.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_customize_control_js() {
	wp_enqueue_script( 'color-scheme-control', get_template_directory_uri() . '/js/color-scheme-control.js', array( 'customize-controls', 'iris', 'underscore', 'wp-util' ), '20150926', true );
	wp_localize_script( 'color-scheme-control', 'colorScheme', hbaintranettheme_get_color_schemes() );
}
add_action( 'customize_controls_enqueue_scripts', 'hbaintranettheme_customize_control_js' );

/**
 * Binds JS handlers to make the Customizer preview reload changes asynchronously.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_customize_preview_js() {
	wp_enqueue_script( 'hbaintranettheme-customize-preview', get_template_directory_uri() . '/js/customize-preview.js', array( 'customize-preview' ), '20150922', true );
}
add_action( 'customize_preview_init', 'hbaintranettheme_customize_preview_js' );

/**
 * Returns CSS for the color schemes.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @param array $colors Color scheme colors.
 * @return string Color scheme CSS.
 */
function hbaintranettheme_get_color_scheme_css( $colors ) {
	$colors = wp_parse_args( $colors, array(
		'background_color'      => '',
		'page_background_color' => '',
		'link_color'            => '',
		'main_text_color'       => '',
		'secondary_text_color'  => '',
		'border_color'          => '',
	) );

	return <<<CSS
	/* Color Scheme */

	/* Background Color */
	body {
		background-color: {$colors['background_color']};
	}

	/* Page Background Color */
	.site {
		background-color: {$colors['page_background_color']};
	}

	mark,
	ins,
	button,
	button[disabled]:hover,
	button[disabled]:focus,
	input[type="button"],
	input[type="button"][disabled]:hover,
	input[type="button"][disabled]:focus,
	input[type="reset"],
	input[type="reset"][disabled]:hover,
	input[type="reset"][disabled]:focus,
	input[type="submit"],
	input[type="submit"][disabled]:hover,
	input[type="submit"][disabled]:focus,
	.menu-toggle.toggled-on,
	.menu-toggle.toggled-on:hover,
	.menu-toggle.toggled-on:focus,
	.pagination .prev,
	.pagination .next,
	.pagination .prev:hover,
	.pagination .prev:focus,
	.pagination .next:hover,
	.pagination .next:focus,
	.pagination .nav-links:before,
	.pagination .nav-links:after,
	.widget_calendar tbody a,
	.widget_calendar tbody a:hover,
	.widget_calendar tbody a:focus,
	.page-links a,
	.page-links a:hover,
	.page-links a:focus {
		color: {$colors['page_background_color']};
	}

	/* Link Color */
	.menu-toggle:hover,
	.menu-toggle:focus,
	a,
	.main-navigation a:hover,
	.main-navigation a:focus,
	.dropdown-toggle:hover,
	.dropdown-toggle:focus,
	.social-navigation a:hover:before,
	.social-navigation a:focus:before,
	.post-navigation a:hover .post-title,
	.post-navigation a:focus .post-title,
	.tagcloud a:hover,
	.tagcloud a:focus,
	.site-branding .site-title a:hover,
	.site-branding .site-title a:focus,
	.entry-title a:hover,
	.entry-title a:focus,
	.entry-footer a:hover,
	.entry-footer a:focus,
	.comment-metadata a:hover,
	.comment-metadata a:focus,
	.pingback .comment-edit-link:hover,
	.pingback .comment-edit-link:focus,
	.comment-reply-link,
	.comment-reply-link:hover,
	.comment-reply-link:focus,
	.required,
	.site-info a:hover,
	.site-info a:focus {
		color: {$colors['link_color']};
	}

	mark,
	ins,
	button:hover,
	button:focus,
	input[type="button"]:hover,
	input[type="button"]:focus,
	input[type="reset"]:hover,
	input[type="reset"]:focus,
	input[type="submit"]:hover,
	input[type="submit"]:focus,
	.pagination .prev:hover,
	.pagination .prev:focus,
	.pagination .next:hover,
	.pagination .next:focus,
	.widget_calendar tbody a,
	.page-links a:hover,
	.page-links a:focus {
		background-color: {$colors['link_color']};
	}

	input[type="text"]:focus,
	input[type="email"]:focus,
	input[type="url"]:focus,
	input[type="password"]:focus,
	input[type="search"]:focus,
	textarea:focus,
	.tagcloud a:hover,
	.tagcloud a:focus,
	.menu-toggle:hover,
	.menu-toggle:focus {
		border-color: {$colors['link_color']};
	}

	/* Main Text Color */
	body,
	blockquote cite,
	blockquote small,
	.main-navigation a,
	.menu-toggle,
	.dropdown-toggle,
	.social-navigation a,
	.post-navigation a,
	.pagination a:hover,
	.pagination a:focus,
	.widget-title a,
	.site-branding .site-title a,
	.entry-title a,
	.page-links > .page-links-title,
	.comment-author,
	.comment-reply-title small a:hover,
	.comment-reply-title small a:focus {
		color: {$colors['main_text_color']};
	}

	blockquote,
	.menu-toggle.toggled-on,
	.menu-toggle.toggled-on:hover,
	.menu-toggle.toggled-on:focus,
	.post-navigation,
	.post-navigation div + div,
	.pagination,
	.widget,
	.page-header,
	.page-links a,
	.comments-title,
	.comment-reply-title {
		border-color: {$colors['main_text_color']};
	}

	button,
	button[disabled]:hover,
	button[disabled]:focus,
	input[type="button"],
	input[type="button"][disabled]:hover,
	input[type="button"][disabled]:focus,
	input[type="reset"],
	input[type="reset"][disabled]:hover,
	input[type="reset"][disabled]:focus,
	input[type="submit"],
	input[type="submit"][disabled]:hover,
	input[type="submit"][disabled]:focus,
	.menu-toggle.toggled-on,
	.menu-toggle.toggled-on:hover,
	.menu-toggle.toggled-on:focus,
	.pagination:before,
	.pagination:after,
	.pagination .prev,
	.pagination .next,
	.page-links a {
		background-color: {$colors['main_text_color']};
	}

	/* Secondary Text Color */

	/**
	 * IE8 and earlier will drop any block with CSS3 selectors.
	 * Do not combine these styles with the next block.
	 */
	body:not(.search-results) .entry-summary {
		color: {$colors['secondary_text_color']};
	}

	blockquote,
	.post-password-form label,
	a:hover,
	a:focus,
	a:active,
	.post-navigation .meta-nav,
	.image-navigation,
	.comment-navigation,
	.widget_recent_entries .post-date,
	.widget_rss .rss-date,
	.widget_rss cite,
	.site-description,
	.author-bio,
	.entry-footer,
	.entry-footer a,
	.sticky-post,
	.taxonomy-description,
	.entry-caption,
	.comment-metadata,
	.pingback .edit-link,
	.comment-metadata a,
	.pingback .comment-edit-link,
	.comment-form label,
	.comment-notes,
	.comment-awaiting-moderation,
	.logged-in-as,
	.form-allowed-tags,
	.site-info,
	.site-info a,
	.wp-caption .wp-caption-text,
	.gallery-caption,
	.widecolumn label,
	.widecolumn .mu_register label {
		color: {$colors['secondary_text_color']};
	}

	.widget_calendar tbody a:hover,
	.widget_calendar tbody a:focus {
		background-color: {$colors['secondary_text_color']};
	}

	/* Border Color */
	fieldset,
	pre,
	abbr,
	acronym,
	table,
	th,
	td,
	input[type="text"],
	input[type="email"],
	input[type="url"],
	input[type="password"],
	input[type="search"],
	textarea,
	.main-navigation li,
	.main-navigation .primary-menu,
	.menu-toggle,
	.dropdown-toggle:after,
	.social-navigation a,
	.image-navigation,
	.comment-navigation,
	.tagcloud a,
	.entry-content,
	.entry-summary,
	.page-links a,
	.page-links > span,
	.comment-list article,
	.comment-list .pingback,
	.comment-list .trackback,
	.comment-reply-link,
	.no-comments,
	.widecolumn .mu_register .mu_alert {
		border-color: {$colors['main_text_color']}; /* Fallback for IE7 and IE8 */
		border-color: {$colors['border_color']};
	}

	hr,
	code {
		background-color: {$colors['main_text_color']}; /* Fallback for IE7 and IE8 */
		background-color: {$colors['border_color']};
	}

	@media screen and (min-width: 56.875em) {
		.main-navigation li:hover > a,
		.main-navigation li.focus > a {
			color: {$colors['link_color']};
		}

		.main-navigation ul ul,
		.main-navigation ul ul li {
			border-color: {$colors['border_color']};
		}

		.main-navigation ul ul:before {
			border-top-color: {$colors['border_color']};
			border-bottom-color: {$colors['border_color']};
		}

		.main-navigation ul ul li {
			background-color: {$colors['page_background_color']};
		}

		.main-navigation ul ul:after {
			border-top-color: {$colors['page_background_color']};
			border-bottom-color: {$colors['page_background_color']};
		}
	}

CSS;
}


/**
 * Outputs an Underscore template for generating CSS for the color scheme.
 *
 * The template generates the css dynamically for instant display in the
 * Customizer preview.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_color_scheme_css_template() {
	$colors = array(
		'background_color'      => '{{ data.background_color }}',
		'page_background_color' => '{{ data.page_background_color }}',
		'link_color'            => '{{ data.link_color }}',
		'main_text_color'       => '{{ data.main_text_color }}',
		'secondary_text_color'  => '{{ data.secondary_text_color }}',
		'border_color'          => '{{ data.border_color }}',
	);
	?>
	<script type="text/html" id="tmpl-hbaintranettheme-color-scheme">
		<?php echo hbaintranettheme_get_color_scheme_css( $colors ); ?>
	</script>
	<?php
}
add_action( 'customize_controls_print_footer_scripts', 'hbaintranettheme_color_scheme_css_template' );

/**
 * Enqueues front-end CSS for the page background color.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see wp_add_inline_style()
 */
function hbaintranettheme_page_background_color_css() {
	$color_scheme          = hbaintranettheme_get_color_scheme();
	$default_color         = $color_scheme[1];
	$page_background_color = get_theme_mod( 'page_background_color', $default_color );

	// Don't do anything if the current color is the default.
	if ( $page_background_color === $default_color ) {
		return;
	}

	$css = '
		/* Custom Page Background Color */
		.site {
			background-color: %1$s;
		}

		mark,
		ins,
		button,
		button[disabled]:hover,
		button[disabled]:focus,
		input[type="button"],
		input[type="button"][disabled]:hover,
		input[type="button"][disabled]:focus,
		input[type="reset"],
		input[type="reset"][disabled]:hover,
		input[type="reset"][disabled]:focus,
		input[type="submit"],
		input[type="submit"][disabled]:hover,
		input[type="submit"][disabled]:focus,
		.menu-toggle.toggled-on,
		.menu-toggle.toggled-on:hover,
		.menu-toggle.toggled-on:focus,
		.pagination .prev,
		.pagination .next,
		.pagination .prev:hover,
		.pagination .prev:focus,
		.pagination .next:hover,
		.pagination .next:focus,
		.pagination .nav-links:before,
		.pagination .nav-links:after,
		.widget_calendar tbody a,
		.widget_calendar tbody a:hover,
		.widget_calendar tbody a:focus,
		.page-links a,
		.page-links a:hover,
		.page-links a:focus {
			color: %1$s;
		}

		@media screen and (min-width: 56.875em) {
			.main-navigation ul ul li {
				background-color: %1$s;
			}

			.main-navigation ul ul:after {
				border-top-color: %1$s;
				border-bottom-color: %1$s;
			}
		}
	';

	wp_add_inline_style( 'hbaintranettheme-style', sprintf( $css, $page_background_color ) );
}
add_action( 'wp_enqueue_scripts', 'hbaintranettheme_page_background_color_css', 11 );

/**
 * Enqueues front-end CSS for the link color.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see wp_add_inline_style()
 */
function hbaintranettheme_link_color_css() {
	$color_scheme    = hbaintranettheme_get_color_scheme();
	$default_color   = $color_scheme[2];
	$link_color = get_theme_mod( 'link_color', $default_color );

	// Don't do anything if the current color is the default.
	if ( $link_color === $default_color ) {
		return;
	}

	$css = '
		/* Custom Link Color */
		.menu-toggle:hover,
		.menu-toggle:focus,
		a,
		.main-navigation a:hover,
		.main-navigation a:focus,
		.dropdown-toggle:hover,
		.dropdown-toggle:focus,
		.social-navigation a:hover:before,
		.social-navigation a:focus:before,
		.post-navigation a:hover .post-title,
		.post-navigation a:focus .post-title,
		.tagcloud a:hover,
		.tagcloud a:focus,
		.site-branding .site-title a:hover,
		.site-branding .site-title a:focus,
		.entry-title a:hover,
		.entry-title a:focus,
		.entry-footer a:hover,
		.entry-footer a:focus,
		.comment-metadata a:hover,
		.comment-metadata a:focus,
		.pingback .comment-edit-link:hover,
		.pingback .comment-edit-link:focus,
		.comment-reply-link,
		.comment-reply-link:hover,
		.comment-reply-link:focus,
		.required,
		.site-info a:hover,
		.site-info a:focus {
			color: %1$s;
		}

		mark,
		ins,
		button:hover,
		button:focus,
		input[type="button"]:hover,
		input[type="button"]:focus,
		input[type="reset"]:hover,
		input[type="reset"]:focus,
		input[type="submit"]:hover,
		input[type="submit"]:focus,
		.pagination .prev:hover,
		.pagination .prev:focus,
		.pagination .next:hover,
		.pagination .next:focus,
		.widget_calendar tbody a,
		.page-links a:hover,
		.page-links a:focus {
			background-color: %1$s;
		}

		input[type="text"]:focus,
		input[type="email"]:focus,
		input[type="url"]:focus,
		input[type="password"]:focus,
		input[type="search"]:focus,
		textarea:focus,
		.tagcloud a:hover,
		.tagcloud a:focus,
		.menu-toggle:hover,
		.menu-toggle:focus {
			border-color: %1$s;
		}

		@media screen and (min-width: 56.875em) {
			.main-navigation li:hover > a,
			.main-navigation li.focus > a {
				color: %1$s;
			}
		}
	';

	wp_add_inline_style( 'hbaintranettheme-style', sprintf( $css, $link_color ) );
}
add_action( 'wp_enqueue_scripts', 'hbaintranettheme_link_color_css', 11 );

/**
 * Enqueues front-end CSS for the main text color.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see wp_add_inline_style()
 */
function hbaintranettheme_main_text_color_css() {
	$color_scheme    = hbaintranettheme_get_color_scheme();
	$default_color   = $color_scheme[3];
	$main_text_color = get_theme_mod( 'main_text_color', $default_color );

	// Don't do anything if the current color is the default.
	if ( $main_text_color === $default_color ) {
		return;
	}

	// Convert main text hex color to rgba.
	$main_text_color_rgb = hbaintranettheme_hex2rgb( $main_text_color );

	// If the rgba values are empty return early.
	if ( empty( $main_text_color_rgb ) ) {
		return;
	}

	// If we get this far, we have a custom color scheme.
	$border_color = vsprintf( 'rgba( %1$s, %2$s, %3$s, 0.2)', $main_text_color_rgb );

	$css = '
		/* Custom Main Text Color */
		body,
		blockquote cite,
		blockquote small,
		.main-navigation a,
		.menu-toggle,
		.dropdown-toggle,
		.social-navigation a,
		.post-navigation a,
		.pagination a:hover,
		.pagination a:focus,
		.widget-title a,
		.site-branding .site-title a,
		.entry-title a,
		.page-links > .page-links-title,
		.comment-author,
		.comment-reply-title small a:hover,
		.comment-reply-title small a:focus {
			color: %1$s
		}

		blockquote,
		.menu-toggle.toggled-on,
		.menu-toggle.toggled-on:hover,
		.menu-toggle.toggled-on:focus,
		.post-navigation,
		.post-navigation div + div,
		.pagination,
		.widget,
		.page-header,
		.page-links a,
		.comments-title,
		.comment-reply-title {
			border-color: %1$s;
		}

		button,
		button[disabled]:hover,
		button[disabled]:focus,
		input[type="button"],
		input[type="button"][disabled]:hover,
		input[type="button"][disabled]:focus,
		input[type="reset"],
		input[type="reset"][disabled]:hover,
		input[type="reset"][disabled]:focus,
		input[type="submit"],
		input[type="submit"][disabled]:hover,
		input[type="submit"][disabled]:focus,
		.menu-toggle.toggled-on,
		.menu-toggle.toggled-on:hover,
		.menu-toggle.toggled-on:focus,
		.pagination:before,
		.pagination:after,
		.pagination .prev,
		.pagination .next,
		.page-links a {
			background-color: %1$s;
		}

		/* Border Color */
		fieldset,
		pre,
		abbr,
		acronym,
		table,
		th,
		td,
		input[type="text"],
		input[type="email"],
		input[type="url"],
		input[type="password"],
		input[type="search"],
		textarea,
		.main-navigation li,
		.main-navigation .primary-menu,
		.menu-toggle,
		.dropdown-toggle:after,
		.social-navigation a,
		.image-navigation,
		.comment-navigation,
		.tagcloud a,
		.entry-content,
		.entry-summary,
		.page-links a,
		.page-links > span,
		.comment-list article,
		.comment-list .pingback,
		.comment-list .trackback,
		.comment-reply-link,
		.no-comments,
		.widecolumn .mu_register .mu_alert {
			border-color: %1$s; /* Fallback for IE7 and IE8 */
			border-color: %2$s;
		}

		hr,
		code {
			background-color: %1$s; /* Fallback for IE7 and IE8 */
			background-color: %2$s;
		}

		@media screen and (min-width: 56.875em) {
			.main-navigation ul ul,
			.main-navigation ul ul li {
				border-color: %2$s;
			}

			.main-navigation ul ul:before {
				border-top-color: %2$s;
				border-bottom-color: %2$s;
			}
		}
	';

	wp_add_inline_style( 'hbaintranettheme-style', sprintf( $css, $main_text_color, $border_color ) );
}
add_action( 'wp_enqueue_scripts', 'hbaintranettheme_main_text_color_css', 11 );

/**
 * Enqueues front-end CSS for the secondary text color.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @see wp_add_inline_style()
 */
function hbaintranettheme_secondary_text_color_css() {
	$color_scheme    = hbaintranettheme_get_color_scheme();
	$default_color   = $color_scheme[4];
	$secondary_text_color = get_theme_mod( 'secondary_text_color', $default_color );

	// Don't do anything if the current color is the default.
	if ( $secondary_text_color === $default_color ) {
		return;
	}

	$css = '
		/* Custom Secondary Text Color */

		/**
		 * IE8 and earlier will drop any block with CSS3 selectors.
		 * Do not combine these styles with the next block.
		 */
		body:not(.search-results) .entry-summary {
			color: %1$s;
		}

		blockquote,
		.post-password-form label,
		a:hover,
		a:focus,
		a:active,
		.post-navigation .meta-nav,
		.image-navigation,
		.comment-navigation,
		.widget_recent_entries .post-date,
		.widget_rss .rss-date,
		.widget_rss cite,
		.site-description,
		.author-bio,
		.entry-footer,
		.entry-footer a,
		.sticky-post,
		.taxonomy-description,
		.entry-caption,
		.comment-metadata,
		.pingback .edit-link,
		.comment-metadata a,
		.pingback .comment-edit-link,
		.comment-form label,
		.comment-notes,
		.comment-awaiting-moderation,
		.logged-in-as,
		.form-allowed-tags,
		.site-info,
		.site-info a,
		.wp-caption .wp-caption-text,
		.gallery-caption,
		.widecolumn label,
		.widecolumn .mu_register label {
			color: %1$s;
		}

		.widget_calendar tbody a:hover,
		.widget_calendar tbody a:focus {
			background-color: %1$s;
		}
	';

	wp_add_inline_style( 'hbaintranettheme-style', sprintf( $css, $secondary_text_color ) );
}
add_action( 'wp_enqueue_scripts', 'hbaintranettheme_secondary_text_color_css', 11 );
