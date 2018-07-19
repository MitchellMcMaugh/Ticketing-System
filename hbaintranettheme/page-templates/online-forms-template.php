<?php
/**
 * Template Name: Online Forms Template
 *
 * Description: Twenty Twelve loves the no-sidebar look as much as
 * you do. Use this page template to remove the sidebar from any page.
 *
 * Tip: to remove the sidebar from all posts and pages simply remove
 * any active widgets from the Main Sidebar area, and the sidebar will
 * disappear everywhere.
 *
 * @package WordPress
 * @subpackage Twenty_Twelve
 * @since Twenty Twelve 1.0
 */
get_header();
?>

<div class="main-content">

	<div class="bread">
		<a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;
		<?php the_field('page_title'); ?>
	</div>

	<div class="lhs-menu">
		<?php if ( has_nav_menu( 'self-service-menu' ) || has_nav_menu( 'social' ) ) : ?>
		<div class="menu">
			<?php if ( has_nav_menu( 'self-service-menu' ) ) : ?>
			<?php
        wp_nav_menu(array(
            'theme_location' => 'self-service-menu',
            'menu_class' => 'self-service-menu'
        ));
        ?>
			<?php endif; ?>
		</div>
		<?php endif; ?>
	</div>

	<div class="center-content">
		<div class="content-head">
			<h2>
				<?php the_field('page_title'); ?>
			</h2>
		</div>

			<div id = "one" class="one"></div>
    		<div id="two" class="two"></div>		
			</div>

		</div>
		<!-- End posts -->
	</div>

	


</div>
