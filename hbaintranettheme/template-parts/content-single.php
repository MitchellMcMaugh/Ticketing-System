<?php
/**
 * The template part for displaying single posts
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */
?>

<article class="news-item" id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
	<div class="content-head">
		<?php hbaintranettheme_entry_meta(); ?>
		<?php the_title( '<h2 class="entry-title">', '</h2>' ); ?>
	</div>

	<!-- Begin posts -->
	<div class="content-middle">
		<div class="ww">
			<p><?php the_field('excerpt'); ?></p>
			<?php if( get_field('promo_image') ): ?>
				<div class="ww-hero-image">
					<img src="<?php the_field('promo_image'); ?>">
				</div>
			<?php endif; ?>

			<?php while(has_sub_field("flexible_content")): ?>

				<?php if(get_row_layout() == "flexible_content"): // layout: Content ?>
			 
					<?php the_sub_field("content"); ?>
					
				<?php elseif(get_row_layout() == "subheading"): // layout: File ?>
			 
					<h3><?php the_sub_field('text'); ?></h3>

				<?php elseif(get_row_layout() == "hero_image"): // layout: File ?>

					<div class="ww-hero-image">
						<img src="<?php the_sub_field('image_hero'); ?>" />
					</div>

				<?php elseif(get_row_layout() == "embed"): // layout: File ?>

					<div class="ww-source-code"><?php the_sub_field('embed_code'); ?></div>
		
				<?php endif; ?>
			
			<?php endwhile; ?>

			<?php if(get_field('wwgallery')): ?>
				<div class="ww-gallery">
				<?php while(has_sub_field('wwgallery')): ?>

				<?php $image = wp_get_attachment_image_src(get_sub_field('photo'), 'medium'); ?>
				<a class="col swipebox" href="<?php echo $image[0]; ?>">
					<div class="photo" style="background-image: url(<?php echo $image[0]; ?>);" alt="<?php echo get_the_title(get_sub_field('photo')) ?>)"></div>
				</a>

				<?php endwhile; ?>
				</div>
			<?php endif; ?>
			
			<?php
global $current_user; 
get_currentuserinfo(); 
if ( user_can( $current_user, "subscriber" ) ){ ?>

<?php }  else { ?>

			<?php echo do_shortcode ('[wp_ulike]'); ?>

<?php } ?>
			
			<?php

				wp_link_pages( array(
					'before'      => '<div class="page-links"><span class="page-links-title">' . __( 'Pages:', 'hbaintranettheme' ) . '</span>',
					'after'       => '</div>',
					'link_before' => '<span>',
					'link_after'  => '</span>',
					'pagelink'    => '<span class="screen-reader-text">' . __( 'Page', 'hbaintranettheme' ) . ' </span>%',
					'separator'   => '<span class="screen-reader-text">, </span>',
				) );

				if ( '' !== get_the_author_meta( 'description' ) ) {
					get_template_part( 'template-parts/biography' );
				}

				// If comments are open or we have at least one comment, load up the comment template.
				if ( comments_open() || get_comments_number() ) {
					comments_template();
				}
			?>
		</div>
	</div>

</article><!-- #post-## -->
