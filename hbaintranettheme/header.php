<?php
$page = $_SERVER['PHP_SELF'];
$sec = "900";
?>

<?php
/**
 * The template for displaying the header
 *
 * Displays all of the head element and everything up until the "site-content" div.
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

?><!DOCTYPE html>
<html <?php language_attributes(); ?> class="no-js">
<head>
	<meta charset="<?php bloginfo( 'charset' ); ?>">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="profile" href="http://gmpg.org/xfn/11">
	<?php if ( is_home() ) { ?>
	<meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">
	<?php } ?>
	<?php if ( is_singular() && pings_open( get_queried_object() ) ) : ?>
	<link rel="pingback" href="<?php bloginfo( 'pingback_url' ); ?>">
	<?php endif; ?>
	<?php wp_head(); ?>
	<link rel="shortcut icon" type="image/x-icon" href="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/favicon.ico">
	<script type="text/javascript" src="<?php echo site_url() ?>/hub-barnacle/barnacle/barnacle.nocache.js"></script>
</head>

<body <?php body_class(); ?>>

<!-- head begin -->


<div class="head">
	<a class="logo" href="<?php echo esc_url( home_url( '/' ) ); ?>" title="Return to the Homepage">the <em>hub</em>.</a>
	<?php if ( has_nav_menu( 'primary' ) || has_nav_menu( 'social' ) ) : ?>
		<a class="toggle" href="#" title="Toggle Mobile Menu">Toggle Menu</a>
		<div class="navigation">
			<?php if ( has_nav_menu( 'primary' ) ) : ?>
				<?php
					wp_nav_menu( array(
						'theme_location' => 'primary',
						'menu_class'     => 'primary-menu',
						'after' => '<span>' . mnp_new_posts_count("cat=29") . '</span>'
					 ) );
				?>
			<?php endif; ?>
			<form method="get" id="searchform" action="<?php bloginfo('home'); ?>/">
				<div class="searchHeadMobile">
					<input class="inputText" type="text" size="put_a_size_here" name="s" id="s" placeholder="Search The Hub" />
					<input class="btnSearch" type="submit" id="searchsubmit" value="Search" />
				</div>
			</form>
			<a class="ww-post" href="/wp-admin/post-new.php?category_id=36">W</a>
			<a class="fc-post" href="/wp-admin/post-new.php?category_id=37">FC</a>
			
		</div>
	<?php endif; ?>
	
	<div class="header-values">generous, creative, genuine and collaborative</div>
	
	<form method="get" id="searchform" action="<?php bloginfo('home'); ?>/">
		<div class="searchHead">
			<input class="inputText" type="text" size="put_a_size_here" name="s" id="s" placeholder="Search The Hub" />
			<input class="btnSearch" type="submit" id="searchsubmit" value="Search" />
		</div>
	</form>
	
</div>
<!-- head end -->

<div id="content" class="site-content">
